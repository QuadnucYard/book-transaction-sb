package team.wuse.koob.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team.wuse.koob.dao.BookDAO;
import team.wuse.koob.entity.Book;

import java.util.List;

@Service
public class BookService {
	@Autowired
	private BookDAO bookDAO;
	/*@Autowired
	private CategoryService categoryService;
	@Autowired
	private RedisService redisService;*/

	public List<Book> list() {
		List<Book> books;
		String key = "booklist";
		//Object bookCache = redisService.get(key);

		//if (bookCache == null) {
			books = bookDAO.findAll(Sort.by(Sort.Direction.DESC, "id"));
		//	redisService.set(key, books);
		//} else {
		//	books = CastUtils.objectConvertToList(bookCache, Book.class);
		//}
		return books;
	}

//    直接用注解实现缓存
//    @Cacheable(value = RedisConfig.REDIS_KEY_DATABASE)
//    public List<Book> list() {
//        List<Book> books;
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
//        books = bookDAO.findAll(sort);
//        return books;
//    }

	public void addOrUpdate(Book book) {
		//redisService.delete("booklist");
		bookDAO.save(book);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//redisService.delete("booklist");
	}

	public void deleteById(int id) {
		//redisService.delete("booklist");
		bookDAO.deleteById(id);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//redisService.delete("booklist");
	}

	/*public List<Book> listByCategory(int cid) {
		Category category = categoryService.get(cid);
		return bookDAO.findAllByCategory(category);
	}*/

	public List<Book> search(String keywords) {
		return bookDAO.findAllByTitleLikeOrAuthorLike('%' + keywords + '%', '%' + keywords + '%');
	}
}
