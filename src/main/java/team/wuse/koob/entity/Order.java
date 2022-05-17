package team.wuse.koob.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "order")
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;//订单编号

    private int price;//订单总价

    private String address;//收获地址

    private Date date;//创建日期

    private int status;//订单状态

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> details;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
