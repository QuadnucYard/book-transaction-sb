package team.wuse.koob.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.wuse.koob.entity.User;
import team.wuse.koob.result.Result;
import team.wuse.koob.result.ResultFactory;
import team.wuse.koob.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;

	@GetMapping("/api/order")
	public Result getOrders(@RequestParam int status) {
		User user = (User)SecurityUtils.getSubject().getPrincipal();
		return ResultFactory.success(orderService.findAllByUserIdAndStatus(user.getId(), status));
	}

}
