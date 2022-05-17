package team.wuse.koob.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_detail")
@ToString
public class OrderDetail {

    // 不会用复合外键
    //@EmbeddedId
    //private OrderDetailPK id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "goods_id", referencedColumnName = "id")
    private Goods goods;//商品的编号

    private String goods_name;//商品的名称

    private int number;//每种商品的数量

    private int price;//每种商品的总价

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
