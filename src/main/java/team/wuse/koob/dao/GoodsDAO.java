package team.wuse.koob.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import team.wuse.koob.entity.Goods;

import java.util.List;

public interface GoodsDAO  extends JpaRepository<Goods, Integer> {

	/**
	 * 根据商品编号查询商品
	 *
	 * @return 查询到的商品实体
	 */
	Goods findById(int id);

	List<Goods> findAllByNameLike(String keyword0);

	List<Goods> findAllByNameLikeOrDescLike(String keyword0, String keyword1);
}
