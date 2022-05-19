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

import java.util.Date;

@RestController
public class GoodsController {

	@Autowired
	GoodsService goodsService;

	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;

	/*@GetMapping("/api/goods/list")
	public Result listGoods() {
		return ResultFactory.buildSuccessResult(goodsService.list());
	}*/

	@GetMapping("/api/goods/list")
	public Result searchGoods(@RequestParam("keywords") String keywords) {
		try {
			return ResultFactory.success("".equals(keywords) ? goodsService.list() : goodsService.search(keywords));
		} catch (Exception e) {
			e.printStackTrace(); // findLike那步有时会error
			return ResultFactory.fail("Internal error");
		}
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
}
