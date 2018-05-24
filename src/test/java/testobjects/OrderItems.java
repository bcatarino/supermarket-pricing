package testobjects;

import com.bcatarino.supermarket.model.OrderItem;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static testobjects.Products.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrderItems {

    public static OrderItem oneCanOfBeans() {
        return new OrderItem(beans(), BigDecimal.ONE);
    }

    public static OrderItem threeCansOfBeans() {
        return new OrderItem(beans(), BigDecimal.valueOf(3d));
    }

    public static OrderItem oneCanOfBeansWithDiscounts() {
        return new OrderItem(beansWithDiscount(), BigDecimal.ONE);
    }

    public static OrderItem threeCansOfBeansWithDiscounts() {
        return new OrderItem(beansWithDiscount(), BigDecimal.valueOf(3d));
    }

    public static OrderItem oneCanOfCoke() {
        return new OrderItem(coke(), BigDecimal.ONE);
    }

    public static OrderItem threeCansOfCoke() {
        return new OrderItem(coke(), BigDecimal.valueOf(3d));
    }

    public static OrderItem threeCansOfCokeWithDiscounts() {
        return new OrderItem(cokeWithDiscount(), BigDecimal.valueOf(3d));
    }

    public static OrderItem oneKgOfOranges() {
        return new OrderItem(oranges(), BigDecimal.ONE);
    }

    public static OrderItem twoHundredGramsOfOranges() {
        return new OrderItem(oranges(), BigDecimal.valueOf(0.2d));
    }

    public static OrderItem threeKgsOfOranges() {
        return new OrderItem(oranges(), BigDecimal.valueOf(3d));
    }
}
