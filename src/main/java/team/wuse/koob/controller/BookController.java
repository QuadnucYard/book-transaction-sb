package team.wuse.koob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.wuse.koob.entity.Book;
import team.wuse.koob.result.Result;
import team.wuse.koob.result.ResultFactory;
import team.wuse.koob.service.BookService;

import javax.validation.Valid;

@RestController
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("/api/book/list")
	public Result listGoods() {
		return ResultFactory.success(bookService.list());
	}

	@PostMapping("/api/admin/content/books")
	public Result addOrUpdateBooks(@RequestBody @Valid Book book) {
		bookService.addOrUpdate(book);
		return ResultFactory.success("修改成功");
	}

	@PostMapping("/api/admin/content/books/delete")
	public Result deleteBook(@RequestBody @Valid Book book) {
		bookService.deleteById(book.getId());
		return ResultFactory.success("删除成功");
	}

	@GetMapping("/api/book/search")
	public Result searchResult(@RequestParam("keywords") String keywords) {
		if ("".equals(keywords)) {
			return ResultFactory.success(bookService.list());
		} else {
			return ResultFactory.success(bookService.search(keywords));
		}
	}
}
