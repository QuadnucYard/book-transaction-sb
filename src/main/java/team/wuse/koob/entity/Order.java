package team.wuse.koob.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "order_index")
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;//订单编号

    private int price;//订单总价

    private String address;//收获地址

    private Date createTime;//创建日期

    private int status;//订单状态

    @JsonManagedReference
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> details;

    private int userId;

}
