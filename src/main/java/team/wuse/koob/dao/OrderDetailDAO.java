package team.wuse.koob.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import team.wuse.koob.entity.OrderDetail;

import java.util.List;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Integer> {

	/**
	 * 根据订单编号查询订单详情信息
	 *
	 * @return 该订单的全部订单详情实体的数组
	 */
	List<OrderDetail> findAllByOrderId(int orderId);
}
