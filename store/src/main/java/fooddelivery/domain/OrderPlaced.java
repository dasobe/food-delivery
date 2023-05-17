package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String userId;
    private String userName;
    private Long foodId;
    private String orderStatus;
    private String userAddress;
    private Long storeId;
    private Integer qty;
}
