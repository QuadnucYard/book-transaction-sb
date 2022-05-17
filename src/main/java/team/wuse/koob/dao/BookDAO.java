package team.wuse.koob.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import team.wuse.koob.entity.Book;

import java.util.List;

public interface BookDAO extends JpaRepository<Book, Integer> {
	List<Book> findAllByTitleLikeOrAuthorLike(String keyword1, String keyword2);
}
