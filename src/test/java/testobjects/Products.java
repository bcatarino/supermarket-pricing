package testobjects;

import com.bcatarino.supermarket.model.Product;
import com.bcatarino.supermarket.model.Unit;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Products {

    public static Product beans() {
        return new Product("beans", BigDecimal.valueOf(0.5d), Unit.QUANTITY);
    }

    public static Product coke() {
        return new Product("coke", BigDecimal.valueOf(0.7d), Unit.QUANTITY);
    }

    public static Product oranges() {
        return new Product("oranges", BigDecimal.valueOf(1.99d), Unit.WEIGHT);
    }
}
