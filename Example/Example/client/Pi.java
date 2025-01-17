package client;

import compute.Task;
import java.math.BigDecimal;
import java.math.MathContext;

public class Pi implements Task<BigDecimal> {
    private static final long serialVersionUID = 227L;
    private final int digits;

    public Pi(int digits) {
        this.digits = digits;
    }

    @Override
    public BigDecimal execute() {
        return computePi(digits);
    }

    public static BigDecimal computePi(int digits) {
        MathContext mc = new MathContext(digits);
        BigDecimal arctan1_5 = arctan(5, mc);
        BigDecimal arctan1_239 = arctan(239, mc);
        BigDecimal pi = arctan1_5.multiply(BigDecimal.valueOf(4), mc)
                                 .subtract(arctan1_239, mc)
                                 .multiply(BigDecimal.valueOf(4), mc);
        return pi;
    }

    public static BigDecimal arctan(int x, MathContext mc) {
        BigDecimal result, term;
        BigDecimal xsq = BigDecimal.valueOf(x).multiply(BigDecimal.valueOf(x), mc);
        result = term = BigDecimal.ONE.divide(BigDecimal.valueOf(x), mc);
        int k = 1;

        while (term.compareTo(BigDecimal.ZERO) != 0) {
            term = term.divide(xsq, mc).divide(BigDecimal.valueOf(2 * k + 1), mc);
            result = (k % 2 == 0) ? result.add(term, mc) : result.subtract(term, mc);
            k++;
        }

        return result;
    }
}
