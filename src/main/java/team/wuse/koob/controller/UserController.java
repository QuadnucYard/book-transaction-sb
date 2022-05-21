package team.wuse.koob.controller;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.wuse.koob.entity.User;
import team.wuse.koob.result.Result;
import team.wuse.koob.result.ResultFactory;
import team.wuse.koob.service.AdminUserRoleService;
import team.wuse.koob.service.UserService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	AdminUserRoleService adminUserRoleService;

	@Resource
	SecurityManager securityManager;

	@GetMapping("/api/user/{uid}")
	public Result getUser(@PathVariable(name = "uid") String id) {
		return ResultFactory.success(userService.get(Integer.parseInt(id)));
	}

	@PutMapping("/api/user/password")
	public Result resetPassword2(@RequestBody Map<String, String> requestUser) {
		User userInDb = userService.get(Integer.parseInt(requestUser.get("id")));
		String username = requestUser.get("username");
		String pwdold = requestUser.get("password"), pwdnew = requestUser.get("newpwd");
		System.out.println(requestUser);
		UsernamePasswordToken token = new UsernamePasswordToken(username, pwdold);
		try {
			securityManager.authenticate(token);
			if (pwdold.equals(pwdnew)) {
				return ResultFactory.fail("新密码和原密码相同！");
			}
			userService.resetPassword(username, pwdnew);
			return ResultFactory.success("修改密码成功");
		} catch (Exception e) {
			// e.printStackTrace();
			return ResultFactory.fail("密码错误");
		}
	}

	@GetMapping("/api/admin/user")
	public Result listUsers() {
		return ResultFactory.success(userService.list());
	}

	@PutMapping("/api/admin/user/status")
	public Result updateUserStatus(@RequestBody @Valid User requestUser) {
		userService.updateUserStatus(requestUser);
		return ResultFactory.success("用户状态更新成功");
	}

	@PutMapping("/api/admin/user/password")
	public Result resetPassword(@RequestBody @Valid User requestUser) {
		userService.resetPassword(requestUser, requestUser.getPassword());
		return ResultFactory.success("重置密码成功");
	}

	@PutMapping("/api/admin/user")
	public Result editUser(@RequestBody @Valid User requestUser) {
		userService.editUser(requestUser);
		return ResultFactory.success("修改用户信息成功");
	}
}
