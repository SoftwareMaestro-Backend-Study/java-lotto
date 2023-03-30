package lotto.domain;

public class Money {
    private static final int MIN_VALUE = 1000;
    private final int value;

    public Money(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value < MIN_VALUE || value % MIN_VALUE != 0) {
            throw new IllegalArgumentException(String.format("[ERROR] 금액은 %d원일 수 없습니다.", value));
        }
    }

    public int getLottoCount() {
        return this.value / MIN_VALUE;
    }

    public double getRateOfProfit(int profit) {
        return (double) profit * 100 / value;
    }
}
