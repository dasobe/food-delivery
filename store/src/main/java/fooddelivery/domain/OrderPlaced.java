package fooddelivery.domain;

import fooddelivery.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String userId;
    private String userName;
    private Long foodId;
    private String userAddress;
    private Long storeId;
    private Integer qty;
    private String status;
    private String foodName;
    
}
