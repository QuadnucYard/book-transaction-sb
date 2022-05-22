package team.wuse.koob.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import team.wuse.koob.entity.Order;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order, Integer> {

	/**
	 * 根据订单编号查找订单
	 *
	 * @return 订单索引实体
	 */
	Order findById(int id);

	/**
	 * 根据用户编号查找订单
	 *
	 * @return 该用户的全部订单索引实体的数组
	 */
	List<Order> findAllByUserId(int uid);

	List<Order> findAllByUserIdAndStatus(int uid, int status);
}
