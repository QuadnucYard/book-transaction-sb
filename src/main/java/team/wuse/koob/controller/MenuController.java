package team.wuse.koob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team.wuse.koob.result.Result;
import team.wuse.koob.result.ResultFactory;
import team.wuse.koob.service.AdminMenuService;

@RestController
public class MenuController {
	@Autowired
	AdminMenuService adminMenuService;

	@GetMapping("/api/menu")
	public Result menu() {
		return ResultFactory.success(adminMenuService.getMenusByCurrentUser());
	}

	@GetMapping("/api/admin/role/menu")
	public Result listAllMenus() {
		return ResultFactory.success(adminMenuService.getMenusByRoleId(1));
	}
}