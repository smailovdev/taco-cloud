package tacos.data;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import tacos.TacoOrder;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(
            String deliveryZip, Date startDate, Date endDate);
    List<TacoOrder> findByDeliveryToAndDeliveryCityAllIgnoresCase(
            String deliveryTo, String deliveryCity);
    List<TacoOrder> findByDeliveryCityOrderByDeliveryTo(String city);
    @Query("Order o where o.deliveryCity=’Seattle’")
    List<TacoOrder> readOrdersDeliveredInSeattle();
}
