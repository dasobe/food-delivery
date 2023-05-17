package fooddelivery.domain;

import fooddelivery.StoreApplication;
import fooddelivery.domain.OrderCanceled;
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
        OrderCanceled orderCanceled = new OrderCanceled(this);
        orderCanceled.publishAfterCommit();
    }

    public static StoreRepository repository() {
        StoreRepository storeRepository = StoreApplication.applicationContext.getBean(
            StoreRepository.class
        );
        return storeRepository;
    }

    public void acceptOrder(AcceptOrderCommand acceptOrderCommand) {
        OrderAccepted orderAccepted = new OrderAccepted(this);
        orderAccepted.publishAfterCommit();
    }

    public void rejectOrder(RejectOrderCommand rejectOrderCommand) {
        OrderCanceled orderCanceled = new OrderCanceled(this);
        orderCanceled.publishAfterCommit();
    }

    public void startCook(StartCookCommand startCookCommand) {
        CookStarted cookStarted = new CookStarted(this);
        cookStarted.publishAfterCommit();
    }

    public void finishCook(FinishCookCommand finishCookCommand) {
        CookFinished cookFinished = new CookFinished(this);
        cookFinished.publishAfterCommit();
    }

    public static void receiveOrder(OrderPlaced orderPlaced) {
        /** Example 1:  new item 
        Store store = new Store();
        repository().save(store);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(store->{
            
            store // do something
            repository().save(store);


         });
        */

    }

    public static void orderCancel(OrderCanceled orderCanceled) {
        /** Example 1:  new item 
        Store store = new Store();
        repository().save(store);

        OrderCanceled orderCanceled = new OrderCanceled(store);
        orderCanceled.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderCanceled.get???()).ifPresent(store->{
            
            store // do something
            repository().save(store);

            OrderCanceled orderCanceled = new OrderCanceled(store);
            orderCanceled.publishAfterCommit();

         });
        */

    }
}
