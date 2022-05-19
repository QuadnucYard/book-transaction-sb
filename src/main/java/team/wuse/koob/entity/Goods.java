package team.wuse.koob.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "goods")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Goods {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id; // 商品编号

	private String name; // 商品名称

	@OneToOne
	@JoinColumn(name = "book_id", referencedColumnName = "id")
	private Book book;

	@OneToOne
	@JoinColumn(name = "seller_id", referencedColumnName = "id")
	private User seller;

	private Date date;

	private int price; // 价格

	@Column(name = "desc_")
	private String desc; // 商品描述

	/*@OneToMany
	@JoinColumn(name = "id", referencedColumnName = "id")
	private List<GoodsImage> images; // 商品图片*/

	@Column(name = "status")
	private int status; // 商品状态

}

