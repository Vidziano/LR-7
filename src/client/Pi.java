package client;

import compute.Task;
import java.math.BigDecimal;

public class Pi implements Task<BigDecimal> {

    private static final long serialVersionUID = 227L;

    private static final BigDecimal FOUR = BigDecimal.valueOf(4);

    private final int digits;

    public Pi(int digits) {
        this.digits = digits;
    }

    @Override
    public BigDecimal execute() {
        return computePi(digits);
    }

    public static BigDecimal computePi(int digits) {
        int scale = digits + 5;
        BigDecimal arctan1_5 = arctan(BigDecimal.valueOf(5), scale);
        BigDecimal arctan1_239 = arctan(BigDecimal.valueOf(239), scale);
        BigDecimal pi = arctan1_5.multiply(FOUR).subtract(arctan1_239).multiply(FOUR);
        return pi.setScale(digits, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal arctan(BigDecimal inverseX, int scale) {
        BigDecimal result, numer, term;
        BigDecimal invX2 = inverseX.multiply(inverseX);
        numer = BigDecimal.ONE.divide(inverseX, scale, BigDecimal.ROUND_HALF_UP);
        result = numer;
        int i = 1;
        do {
            numer = numer.divide(invX2, scale, BigDecimal.ROUND_HALF_UP);
            int denom = 2 * i + 1;
            term = numer.divide(BigDecimal.valueOf(denom), scale, BigDecimal.ROUND_HALF_UP);
            if ((i % 2) != 0) {
                result = result.subtract(term);
            } else {
                result = result.add(term);
            }
            i++;
        } while (term.compareTo(BigDecimal.ZERO) != 0);
        return result;
    }
}
