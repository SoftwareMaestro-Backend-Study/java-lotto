package lotto.domain.result;

public class ProfitRate {

    private static final int CIPHER = 10;

    private final double value;

    private ProfitRate(double value) {
        this.value = value;
    }

    public static ProfitRate from(int prize, int purchasingMoney) {
        return new ProfitRate(round(((double) prize / purchasingMoney) * 100));
    }

    public double getValue() {
        return value;
    }

    private static double round(double profitRate) {
        return Math.round(profitRate * CIPHER) / (double) CIPHER;
    }
}
