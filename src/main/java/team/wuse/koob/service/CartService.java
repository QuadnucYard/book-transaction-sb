package team.wuse.koob.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.wuse.koob.dao.CartDAO;
import team.wuse.koob.entity.Cart;
import team.wuse.koob.entity.Goods;

@Service
public class CartService {

	@Autowired
	private CartDAO cartDAO;

	public Cart findByUserId(int userId) {
		return cartDAO.findByUserId(userId);
	}

	public Cart add(int userId, Goods goods) {
		Cart cart = findByUserId(userId);
		if (null == cart) {
			cart = new Cart();
			cart.setUserId(userId);
			cart = cartDAO.save(cart);
		}
		cart.getGoods().add(goods);
		cart = cartDAO.save(cart);
		return cart;
	}
}
