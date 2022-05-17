package team.wuse.koob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.wuse.koob.result.Result;
import team.wuse.koob.result.ResultFactory;
import team.wuse.koob.service.GoodsService;

@RestController
public class GoodsController {

	@Autowired
	GoodsService goodsService;

	/*@GetMapping("/api/goods/list")
	public Result listGoods() {
		return ResultFactory.buildSuccessResult(goodsService.list());
	}*/

	@GetMapping("/api/goods/list")
	public Result searchGoods(@RequestParam("keywords") String keywords) {
		try {
			return ResultFactory.buildSuccessResult("".equals(keywords) ? goodsService.list() : goodsService.search(keywords));
		} catch (Exception e) {
			e.printStackTrace(); // findLike那步有时会error
			return  ResultFactory.buildFailResult("Internal error");
		}
	}
}
