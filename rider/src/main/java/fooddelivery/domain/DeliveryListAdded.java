package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class DeliveryListAdded extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long storeId;
    private Long foodId;
    private String status;
    private String userId;
    private String userName;
    private String userAddress;

    public DeliveryListAdded(Delivery aggregate) {
        super(aggregate);
    }

    public DeliveryListAdded() {
        super();
    }
}
