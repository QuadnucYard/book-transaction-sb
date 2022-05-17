package team.wuse.koob.dto;

import lombok.Data;
import lombok.ToString;
import team.wuse.koob.dto.base.OutputConverter;
import team.wuse.koob.entity.Book;
import team.wuse.koob.entity.Goods;
import team.wuse.koob.entity.GoodsImage;
import team.wuse.koob.entity.User;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class GoodsDTO implements OutputConverter<GoodsDTO, Goods> {

	private int id;

	private String name;

	private Book book;

	private UserDTO seller;

	private Date date;

	private int price;

	private String desc;

	private List<GoodsImage> images;

	private int status;

	public GoodsDTO setSeller(User seller) {
		this.seller = new UserDTO().convertFrom(seller);
		return this;
	}
}
