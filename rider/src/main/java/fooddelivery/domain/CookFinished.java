package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class CookFinished extends AbstractEvent {

    private Long id;
    private String status;
    private Long foodId;
    private Long orderId;
    private String foodName;
    private String userId;
    private String userName;
    private String userAddress;
    private Integer qty;
}
