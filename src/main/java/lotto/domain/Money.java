package lotto.domain;

public class Money {
    private static final int LOTTO_AMOUNT = 1000;

    private final int value;

    public Money(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value <= 0 || value % LOTTO_AMOUNT != 0) {
            throw new IllegalArgumentException(String.format("[ERROR] 금액은 %d일 수 없습니다.", value));
        }
    }

    public boolean canBuy(int lottoCount) {
        return this.value / LOTTO_AMOUNT >= lottoCount;
    }

    public int remainCount(int lottoCount) {
        return this.value / LOTTO_AMOUNT - lottoCount;
    }
}
