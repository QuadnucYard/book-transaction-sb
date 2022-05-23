package team.wuse.koob.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
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

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

	@Autowired
	UserService userService;

	@CrossOrigin
	@PostMapping("/api/auth/login")
	public Result login(@RequestBody User requestUser, HttpServletRequest request) {
		System.out.println("login " + requestUser.toString()+" "+request.getSession().getId());

		// 验证码
		/*String sessioncode = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (!requestUser.getCode().equalsIgnoreCase(sessioncode)) {
			System.out.println("验证码: " + sessioncode+ request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY));
			return ResultFactory.fail("验证码错误");
		}*/

		String username = HtmlUtils.htmlEscape(requestUser.getUsername());
		Subject subject = SecurityUtils.getSubject();
//        subject.getSession().setTimeout(10000);
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, requestUser.getPassword());
		usernamePasswordToken.setRememberMe(true);
		try {
			subject.login(usernamePasswordToken);
			User user = userService.findByUsername(username);
			if (!user.isEnabled()) {
				return ResultFactory.fail("该用户已被禁用");
			}
			JSONObject json = JSONUtil.createObj();
			json.put("uid", user.getId());
			json.put("name", username);
			json.put("money", user.getMoney());
			return ResultFactory.success(json);
		} catch (IncorrectCredentialsException e) {
			return ResultFactory.fail("密码错误");
		} catch (UnknownAccountException e) {
			return ResultFactory.fail("账号不存在");
		}
	}

	@PostMapping("/api/auth/register")
	public Result register(@RequestBody User user) {
		System.out.println("register " + user.toString());
		int status = userService.register(user);
		switch (status) {
			case 0:
				return ResultFactory.fail("用户名和密码不能为空");
			case 1:
				return ResultFactory.success("注册成功");
			case 2:
				return ResultFactory.fail("用户已存在");
		}
		return ResultFactory.fail("未知错误");
	}

	@GetMapping("/api/auth/logout")
	public Result logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return ResultFactory.success("成功登出");
	}

	@GetMapping("/api/auth")
	public String authentication() {
		return "身份认证成功";
	}
}