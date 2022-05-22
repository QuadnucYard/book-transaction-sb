package team.wuse.koob.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.wuse.koob.dao.OrderDAO;
import team.wuse.koob.dao.OrderDetailDAO;
import team.wuse.koob.entity.Order;
import team.wuse.koob.entity.OrderDetail;

import java.util.List;

@Service
public class OrderService {

	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private OrderDetailDAO orderDetailDAO;

	/**
	 * 插入订单索引信息
	 *
	 * @return
	 */
	public int addOrder(Order order) {
		orderDAO.save(order);
		return 1;
	}

	/**
	 * 插入订单详情信息
	 *
	 * @return
	 */
	public boolean addOrderDetail(OrderDetail orderDetail) {
		orderDetailDAO.save(orderDetail);
		return true;
	}

	/**
	 * 查找用户的所有订单
	 *
	 * @return
	 */
	public List<Order> findAllByUserId(int uid) {
		return orderDAO.findAllByUserId(uid);
	}

	public List<Order> findAllByUserIdAndStatus(int uid, int status) {
		return status == -1 ? findAllByUserId(uid) : orderDAO.findAllByUserIdAndStatus(uid, status);
	}

	/**
	 * 查找订单的所有信息
	 *
	 * @return
	 */
	public List<OrderDetail> findAllByOrderId(int orderId) {
		return orderDetailDAO.findAllByOrderId(orderId);
	}
}
