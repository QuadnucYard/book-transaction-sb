package team.wuse.koob.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import team.wuse.koob.entity.User;

public interface UserDAO extends JpaRepository<User, Integer> {
	User findByUsername(String username);

	User getByUsernameAndPassword(String username, String password);
}