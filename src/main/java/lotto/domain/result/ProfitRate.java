package lotto.domain.result;

public class ProfitRate {

    private final double value;

    private ProfitRate(double value) {
        this.value = value;
    }

    public static ProfitRate from(int prize, int purchasingMoney) {
        return new ProfitRate(((double) prize / purchasingMoney) * 100);
    }

    @Override
    public String toString() {
        return String.format("%.1f", value);
    }
}
