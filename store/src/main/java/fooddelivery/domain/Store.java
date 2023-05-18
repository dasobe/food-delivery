package fooddelivery.domain;

import fooddelivery.StoreApplication;
import fooddelivery.domain.StoreOrderCanceled;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Store_table")
@Data
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String status;

    private Long foodId;

    private Long orderId;

    private String foodName;

    private String userId;

    private String userName;

    private String userAddress;

    private Integer qty;

    @PostPersist
    public void onPostPersist() {}

    @PostUpdate
    public void onPostUpdate() {
        StoreOrderCanceled storeOrderCanceled = new StoreOrderCanceled(this);
        storeOrderCanceled.publishAfterCommit();
    }

    public static StoreRepository repository() {
        StoreRepository storeRepository = StoreApplication.applicationContext.getBean(
            StoreRepository.class
        );
        return storeRepository;
    }

    public void acceptOrder(AcceptOrderCommand acceptOrderCommand) {
        this.setStatus("OrderAccepted");
        OrderAccepted orderAccepted = new OrderAccepted(this);
        orderAccepted.publishAfterCommit();
    }

    public void rejectOrder(RejectOrderCommand rejectOrderCommand) {
        this.setStatus("OrderRejected");
        OrderCanceled orderCanceled = new OrderCanceled(this);
        orderCanceled.publishAfterCommit();
    }

    public void startCook(StartCookCommand startCookCommand) {
        this.setStatus("CookingStarted");
        CookStarted cookStarted = new CookStarted(this);
        cookStarted.publishAfterCommit();
    }

    public void finishCook(FinishCookCommand finishCookCommand) {
        this.setStatus("CookingFinished");
        CookFinished cookFinished = new CookFinished(this);
        cookFinished.publishAfterCommit();
    }

    public static void receiveOrder(OrderPlaced orderPlaced) {
        Store store = new Store();
        store.setFoodId(orderPlaced.getFoodId());
        store.setFoodName(orderPlaced.getFoodName());
        store.setOrderId(orderPlaced.getId());
        store.setQty(orderPlaced.getQty());
        store.setStatus(orderPlaced.getStatus());
        store.setUserAddress(orderPlaced.getUserAddress());
        store.setUserId(orderPlaced.getUserId());
        
        repository().save(store);

    }

    public static void orderCancel(OrderCanceled orderCanceled) {

        /** Example 1:  new item 
        Store store = new Store();
        repository().save(store);

        StoreOrderCanceled storeOrderCanceled = new StoreOrderCanceled(store);
        storeOrderCanceled.publishAfterCommit();
        */

        
        repository().findByOrderId(orderCanceled.getOrderId()).ifPresent(store->{
            
            store.setStatus("StoreOrderCanceled");
            repository().save(store);

            StoreOrderCanceled storeOrderCanceled = new StoreOrderCanceled(store);
            storeOrderCanceled.publishAfterCommit();

         });



    }
}
