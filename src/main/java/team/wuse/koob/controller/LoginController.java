package team.wuse.koob.controller;

import com.google.code.kaptcha.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import team.wuse.koob.entity.User;
import team.wuse.koob.result.Result;
import team.wuse.koob.result.ResultFactory;
import team.wuse.koob.service.UserService;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

	@Autowired
	UserService userService;

	@CrossOrigin
	@PostMapping("/api/auth/login")
	public Result login(@RequestBody User requestUser, HttpSession session) {
		System.out.println("login " + requestUser.toString());

		// 验证码
		String sessioncode = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (!requestUser.getCode().equalsIgnoreCase(sessioncode)) {
			return ResultFactory.buildFailResult("验证码错误");
		}

		String username = HtmlUtils.htmlEscape(requestUser.getUsername());
		Subject subject = SecurityUtils.getSubject();
//        subject.getSession().setTimeout(10000);
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, requestUser.getPassword());
		usernamePasswordToken.setRememberMe(true);
		try {
			subject.login(usernamePasswordToken);
			User user = userService.findByUsername(username);
			if (!user.isEnabled()) {
				return ResultFactory.buildFailResult("该用户已被禁用");
			}
			return ResultFactory.buildSuccessResult(username);
		} catch (IncorrectCredentialsException e) {
			return ResultFactory.buildFailResult("密码错误");
		} catch (UnknownAccountException e) {
			return ResultFactory.buildFailResult("账号不存在");
		}
	}

	@PostMapping("/api/auth/register")
	public Result register(@RequestBody User user) {
		System.out.println("register " + user.toString());
		int status = userService.register(user);
		switch (status) {
			case 0:
				return ResultFactory.buildFailResult("用户名和密码不能为空");
			case 1:
				return ResultFactory.buildSuccessResult("注册成功");
			case 2:
				return ResultFactory.buildFailResult("用户已存在");
		}
		return ResultFactory.buildFailResult("未知错误");
	}

	@GetMapping("/api/auth/logout")
	public Result logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return ResultFactory.buildSuccessResult("成功登出");
	}

	@GetMapping("/api/auth")
	public String authentication() {
		return "身份认证成功";
	}
}