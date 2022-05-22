package team.wuse.koob.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import team.wuse.koob.entity.Cart;

public interface CartDAO extends JpaRepository<Cart, Integer> {

	Cart findById(int id);

	Cart findByUserId(int userId);
}