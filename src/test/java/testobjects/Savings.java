package testobjects;

import com.bcatarino.supermarket.DiscountType;
import com.bcatarino.supermarket.model.Saving;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Savings {

    public static Saving beansSaving() {
        return new Saving("beans", DiscountType.THREE_FOR_TWO, BigDecimal.valueOf(0.5d));
    }

    public static Saving cokeSaving() {
        return new Saving("coke", DiscountType.TWO_FOR_1_POUND, BigDecimal.valueOf(0.3d));
    }
}
