package fooddelivery.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "OrderStatus_table")
@Data
public class OrderStatus {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String userId;
    private Long orderId;
    private Long storeId;
    private Long foodId;
    private String foodName;
    private Integer qty;
    private String status;
}
