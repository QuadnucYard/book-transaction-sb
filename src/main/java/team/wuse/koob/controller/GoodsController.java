package team.wuse.koob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.wuse.koob.entity.Book;
import team.wuse.koob.entity.Goods;
import team.wuse.koob.result.Result;
import team.wuse.koob.result.ResultFactory;
import team.wuse.koob.service.BookService;
import team.wuse.koob.service.GoodsService;
import team.wuse.koob.service.UserService;

import javax.validation.Valid;
import java.util.Date;

@RestController
public class GoodsController {

	@Autowired
	GoodsService goodsService;

	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;

	@GetMapping("/api/goods/list")
	public Result searchGoods(@RequestParam("keywords") String keywords) {
		return ResultFactory.success("".equals(keywords) ? goodsService.list() : goodsService.search(keywords));
	}

	@GetMapping("/api/goods")
	public  Result getGoods(@RequestParam int id) {
		return ResultFactory.success(goodsService.find(id));
	}

	@PostMapping("/api/goods/post")
	public Result postGoods(@RequestBody Goods goods) {
		goods.setDate(new Date());
		System.out.println(goods);
		Book book = bookService.addOrUpdate(goods.getBook());
		goods.setBook(book);
		goods.setSeller(userService.findByUsername("add"));
		goodsService.addOrUpdate(goods);
		return ResultFactory.success("成功发布");
	}

	@GetMapping("/api/goods/seller")
	public Result getGoodsBySeller(@RequestParam int uid) {
		var result = goodsService.findAllBySellerId(uid);
		return ResultFactory.success(result);
	}

	@PostMapping("/api/admin/content/goods")
	public Result addOrUpdateGoods(@RequestBody @Valid Goods goods) {
		goodsService.addOrUpdate(goods);
		return ResultFactory.success("修改成功");
	}

	@PostMapping("/api/admin/content/goods/delete")
	public Result deleteGoods(@RequestBody @Valid Goods goods) {
		goodsService.deleteById(goods.getId());
		return ResultFactory.success("删除成功");
	}

	@PutMapping("/api/admin/content/goods/status")
	public Result changeGoodsStatus(@RequestBody @Valid Goods goods) {
		Goods goodsInDb = goodsService.find(goods.getId());
		goodsInDb.setStatus(goods.getStatus());
		goodsService.addOrUpdate(goodsInDb);
		return ResultFactory.success("修改状态成功");
	}
}
