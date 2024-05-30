package client;

import compute.Task;
import java.math.BigDecimal;

public class E implements Task<BigDecimal> {

    private static final long serialVersionUID = 227L;

    private final int digits;

    public E(int digits) {
        this.digits = digits;
    }

    @Override
    public BigDecimal execute() {
        return computeE(digits);
    }

    public static BigDecimal computeE(int digits) {
        BigDecimal e = BigDecimal.ZERO;
        BigDecimal factorial = BigDecimal.ONE;
        int scale = digits + 5;
        for (int i = 0; i < digits + 5; i++) {
            if (i > 0) {
                factorial = factorial.multiply(BigDecimal.valueOf(i));
            }
            BigDecimal term = BigDecimal.ONE.divide(factorial, scale, BigDecimal.ROUND_HALF_UP);
            e = e.add(term);
        }
        return e.setScale(digits, BigDecimal.ROUND_HALF_UP);
    }
}
