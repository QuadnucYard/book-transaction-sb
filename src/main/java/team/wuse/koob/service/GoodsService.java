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

	public List<GoodsDTO> list() {
		List<Goods> goodsList = goodsDAO.findAll(Sort.by(Sort.Direction.DESC, "id"));
		List<GoodsDTO> goodsDTOs = goodsList.stream()
				.map(goods -> new GoodsDTO().convertFrom(goods).setSeller(goods.getSeller())).collect(Collectors.toList());
		return goodsDTOs;
	}

	public Goods find(int id) {
		return goodsDAO.findById(id);
	}

	public void addOrUpdate(Goods goods) {
		goodsDAO.save(goods);
		/*try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
	}

	public void deleteById(int id) {
		goodsDAO.deleteById(id);
		/*try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
	}

	public List<Goods> search(String keywords) {
		// TODO 根据卖家名查询
		return goodsDAO.findAllByNameLikeOrDescLike('%' + keywords + '%', '%' + keywords + '%');
	}
}
