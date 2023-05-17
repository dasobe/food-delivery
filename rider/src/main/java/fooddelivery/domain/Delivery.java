package fooddelivery.domain;

import fooddelivery.RiderApplication;
import fooddelivery.domain.DeliveryListAdded;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Delivery_table")
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private Long storeId;

    private Long foodId;

    private String status;

    private String userId;

    private String userName;

    private String userAddress;

    @PostPersist
    public void onPostPersist() {
        // DeliveryListAdded deliveryListAdded = new DeliveryListAdded(this);
        // deliveryListAdded.publishAfterCommit();
    }

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = RiderApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    public void pick(PickCommand pickCommand) {
        DeliveryStarted deliveryStarted = new DeliveryStarted(this);
        deliveryStarted.publishAfterCommit();
    }

    public void deliveryComplete(
        DeliveryCompleteCommand deliveryCompleteCommand
    ) {
        DeliveryCompleted deliveryCompleted = new DeliveryCompleted(this);
        deliveryCompleted.publishAfterCommit();
    }

    public static void addDeliveryList(CookFinished cookFinished) {
        // /** Example 1:  new item 
        Delivery delivery = new Delivery();
        delivery.setFoodId(cookFinished.getFoodId());
        delivery.setOrderId(cookFinished.getOrderId());
        delivery.setStatus("DeliveryStarted");
        delivery.setStoreId(cookFinished.getStoreId());
        delivery.setUserAddress(cookFinished.getUserAddress());
        delivery.setUserId(cookFinished.getUserId());
        delivery.setUserName(cookFinished.getUserId());
        repository().save(delivery);

        DeliveryListAdded deliveryListAdded = new DeliveryListAdded(delivery);
        deliveryListAdded.publishAfterCommit();


        /** Example 2:  finding and process
        
        repository().findById(cookFinished.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);

            DeliveryListAdded deliveryListAdded = new DeliveryListAdded(delivery);
            deliveryListAdded.publishAfterCommit();

         });
        */

    }
}
