package team.wuse.koob.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team.wuse.koob.dao.GoodsDAO;
import team.wuse.koob.dto.GoodsDTO;
import team.wuse.koob.entity.Goods;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsService {

	@Autowired
	private GoodsDAO goodsDAO;

	private List<GoodsDTO> toDTO(List<Goods> goodsList) {
		return goodsList.stream()
				.map(goods -> new GoodsDTO().convertFrom(goods).setSeller(goods.getSeller()))
				.collect(Collectors.toList());
	}

	public List<GoodsDTO> list() {
		return toDTO(goodsDAO.findAll(Sort.by(Sort.Direction.DESC, "id")));
	}

	public List<GoodsDTO> list(int status) {
		if (status == -1) return list();
		return list().stream().filter(t -> t.getStatus() == status).collect(Collectors.toList());
	}

	public Goods find(int id) {
		return goodsDAO.findById(id);
	}

	public void addOrUpdate(Goods goods) {
		goodsDAO.save(goods);
	}

	public void deleteById(int id) {
		goodsDAO.deleteById(id);
	}

	public List<GoodsDTO> search(String keywords) {
		// TODO 根据卖家名查询
		return toDTO(goodsDAO.findAllByNameLike('%' + keywords + '%'));
		//return goodsDAO.findAllByNameLikeOrDescLike('%' + keywords + '%', '%' + keywords + '%');
	}

	public List<GoodsDTO> search(String keywords, int status) {
		if ("".equals(keywords)) {
			return list(status);
		} else if (status == -1) {
			return toDTO(goodsDAO.findAllByNameLike('%' + keywords + '%'));
		} else {
			return toDTO(goodsDAO.findAllByStatusAndNameLike(status, '%' + keywords + '%'));
		}
	}

	public List<GoodsDTO> findAllBySellerId(int id) {
		return toDTO(goodsDAO.findAllBySellerId(id));
	}
}
