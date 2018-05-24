package testobjects;

import com.bcatarino.supermarket.model.OrderItem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static testobjects.Products.*;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrderItems {

    public static OrderItem oneCanOfBeans() {
        return new OrderItem(beans(), BigDecimal.ONE);
    }

    public static OrderItem threeCansOfBeans() {
        return new OrderItem(beans(), BigDecimal.valueOf(3d));
    }

    public static OrderItem oneCanOfCoke() {
        return new OrderItem(coke(), BigDecimal.ONE);
    }

    public static OrderItem threeCansOfCoke() {
        return new OrderItem(coke(), BigDecimal.valueOf(3d));
    }
}
