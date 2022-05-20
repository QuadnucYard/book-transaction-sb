package team.wuse.koob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team.wuse.koob.result.Result;
import team.wuse.koob.result.ResultFactory;
import team.wuse.koob.service.BookService;

@RestController
public class BookController {

	/*
	在发布界面，需要支持查询所有书名、作者、出版社
	也要
	直接返回所有书
	 */

	@Autowired
	BookService bookService;

	@GetMapping("/api/book/list")
	public Result listGoods() {
		return ResultFactory.success(bookService.list());
	}

}
