package team.wuse.koob.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart")
@ToString
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToMany(targetEntity = Goods.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "cart_goods", joinColumns = {@JoinColumn(name = "cart_id")},
			inverseJoinColumns = {@JoinColumn(name = "goods_id")}
	)
	private List<Goods> goods = new ArrayList<>();

	private int userId;
}
