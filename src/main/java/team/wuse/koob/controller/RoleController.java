package team.wuse.koob.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.wuse.koob.entity.AdminRole;
import team.wuse.koob.result.Result;
import team.wuse.koob.result.ResultFactory;
import team.wuse.koob.service.AdminPermissionService;
import team.wuse.koob.service.AdminRoleMenuService;
import team.wuse.koob.service.AdminRolePermissionService;
import team.wuse.koob.service.AdminRoleService;

import java.util.List;
import java.util.Map;

/**
 * Role controller.
 *
 * @author Evan
 * @date 2019/11
 */
@RestController
public class RoleController {
	@Autowired
	AdminRoleService adminRoleService;
	@Autowired
	AdminPermissionService adminPermissionService;
	@Autowired
	AdminRolePermissionService adminRolePermissionService;
	@Autowired
	AdminRoleMenuService adminRoleMenuService;

	@GetMapping("/api/admin/role")
	public Result listRoles() {
		return ResultFactory.success(adminRoleService.listWithPermsAndMenus());
	}

	@PutMapping("/api/admin/role/status")
	public Result updateRoleStatus(@RequestBody AdminRole requestRole) {
		AdminRole adminRole = adminRoleService.updateRoleStatus(requestRole);
		String message = "用户" + adminRole.getNameZh() + "状态更新成功";
		return ResultFactory.success(message);
	}

	@PutMapping("/api/admin/role")
	public Result editRole(@RequestBody AdminRole requestRole) {
		adminRoleService.addOrUpdate(requestRole);
		adminRolePermissionService.savePermChanges(requestRole.getId(), requestRole.getPerms());
		String message = "修改角色信息成功";
		return ResultFactory.success(message);
	}


	@PostMapping("/api/admin/role")
	public Result addRole(@RequestBody AdminRole requestRole) {
		adminRoleService.editRole(requestRole);
		return ResultFactory.success("修改用户成功");
	}

	@GetMapping("/api/admin/role/perm")
	public Result listPerms() {
		return ResultFactory.success(adminPermissionService.list());
	}

	@PutMapping("/api/admin/role/menu")
	public Result updateRoleMenu(@RequestParam int rid, @RequestBody Map<String, List<Integer>> menusIds) {
		adminRoleMenuService.updateRoleMenu(rid, menusIds);
		return ResultFactory.success("更新成功");
	}
}

