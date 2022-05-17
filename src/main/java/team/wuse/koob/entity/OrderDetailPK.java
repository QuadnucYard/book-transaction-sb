package team.wuse.koob.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailPK implements Serializable {
	private int orderId;
	private int goodsId;
}
