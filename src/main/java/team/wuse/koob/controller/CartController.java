package team.wuse.koob.controller;

import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.wuse.koob.entity.Cart;
import team.wuse.koob.entity.Order;
import team.wuse.koob.entity.OrderDetail;
import team.wuse.koob.result.Result;
import team.wuse.koob.result.ResultFactory;
import team.wuse.koob.service.CartService;
import team.wuse.koob.service.GoodsService;
import team.wuse.koob.service.OrderService;
import team.wuse.koob.service.UserService;

import java.util.Date;
import java.util.stream.Collectors;

@RestController
public class CartController {

	@Autowired
	GoodsService goodsService;

	@Autowired
	OrderService orderService;

	@Autowired
	CartService cartService;

	@Autowired
	UserService userService;

	@GetMapping("/api/cart/list")
	public Result listGoods(@RequestParam("uid") int uid) {
		return ResultFactory.success(cartService.findByUserId(uid));
	}

	/*@PutMapping("/api/cart/add")
	public Result addGoods(@RequestBody JSONObject params) {
		int uid = params.getInt("uid");
		int gid = params.getInt("gid");
		Cart result = cartService.add(uid, goodsService.find(gid));
		return ResultFactory.success(result);
	}*/
	@PostMapping("/api/cart/add")
	public Result addGoods(@RequestBody JSONObject params) {
		int uid = params.getInt("uid");
		int gid = params.getInt("gid");
		Cart result = cartService.add(uid, goodsService.find(gid));
		return ResultFactory.success(result);
	}

	@PostMapping("/api/order/create")
	public Result createOrder(@RequestBody JSONObject obj) {
		// 创建订单
		Order order = new Order();
		order.setAddress(obj.getStr("address"));
		order.setCreateTime(new Date());
		order.setUserId(obj.getInt("uid"));
		order.setDetails(obj.getJSONArray("list").stream()
				.map(t -> new OrderDetail(0, goodsService.find((int) t), goodsService.find((int) t).getPrice(), order))
				.collect(Collectors.toList()));
		order.calcPrice();
		orderService.addOrder(order);
		// 清空购物车里的项
		cartService.removeCartGoodsById(obj.getInt("uid"),
				obj.getJSONArray("list").stream().map(t->(int)t).collect(Collectors.toList()));
		return ResultFactory.success("");
	}

}
