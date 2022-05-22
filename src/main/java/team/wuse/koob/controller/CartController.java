package team.wuse.koob.controller;

import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.wuse.koob.entity.Cart;
import team.wuse.koob.result.Result;
import team.wuse.koob.result.ResultFactory;
import team.wuse.koob.service.CartService;
import team.wuse.koob.service.GoodsService;
import team.wuse.koob.service.UserService;

@RestController
public class CartController {

	@Autowired
	GoodsService goodsService;

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

}
