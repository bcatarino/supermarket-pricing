package testobjects;

import com.bcatarino.supermarket.DiscountType;
import com.bcatarino.supermarket.model.Product;
import com.bcatarino.supermarket.model.Unit;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Products {

    public static Product beans() {
        return new Product("beans", BigDecimal.valueOf(0.5d), Unit.QUANTITY);
    }

    public static Product beansWithDiscount() {
        return new Product("beans", BigDecimal.valueOf(0.5d), Unit.QUANTITY, Optional.of(DiscountType.THREE_FOR_TWO));
    }

    public static Product coke() {
        return new Product("coke", BigDecimal.valueOf(0.7d), Unit.QUANTITY);
    }

    public static Product cokeWithDiscount() {
        return new Product("coke", BigDecimal.valueOf(0.7d), Unit.QUANTITY, Optional.of(DiscountType.TWO_FOR_1_POUND));
    }

    public static Product oranges() {
        return new Product("oranges", BigDecimal.valueOf(1.99d), Unit.WEIGHT);
    }
}
