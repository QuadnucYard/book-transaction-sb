package team.wuse.koob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.wuse.koob.entity.User;
import team.wuse.koob.result.Result;
import team.wuse.koob.result.ResultFactory;
import team.wuse.koob.service.AdminUserRoleService;
import team.wuse.koob.service.UserService;

import javax.validation.Valid;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	AdminUserRoleService adminUserRoleService;

	@GetMapping("/api/user/{uid}")
	public Result getUser(@PathVariable(name = "uid") String id) {
		return ResultFactory.success(userService.get(Integer.parseInt(id)));
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
		userService.resetPassword(requestUser);
		return ResultFactory.success("重置密码成功");
	}

	@PutMapping("/api/admin/user")
	public Result editUser(@RequestBody @Valid User requestUser) {
		userService.editUser(requestUser);
		return ResultFactory.success("修改用户信息成功");
	}
}
