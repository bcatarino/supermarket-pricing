package testutils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MathUtils {

    public static BigDecimal bigDecimalScaleTwo(double d) {
        return BigDecimal.valueOf(d).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
