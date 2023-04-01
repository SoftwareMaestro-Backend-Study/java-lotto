package lotto.domain.result;

import java.util.Arrays;

public enum LottoResult {

    FIRST_PRIZE(6, false, 2_000_000_000),
    SECOND_PRIZE(5, true, 30_000_000),
    THIRD_PRIZE(5, false, 1_500_000),
    FOURTH_PRIZE(4, false, 50_000),
    FIFTH_PRIZE(3, false, 5_000),
    NO_PRIZE(0, false, 0);

    private final int matchingNumber;
    private final boolean hasBonusNumber;
    private final int prize;

    LottoResult(int matchingNumber, boolean hasBonusNumber, int prize) {
        this.matchingNumber = matchingNumber;
        this.hasBonusNumber = hasBonusNumber;
        this.prize = prize;
    }

    public static LottoResult find(int matchingNumber, boolean hasBonusNumber) {
        return Arrays.stream(values())
                .filter(value -> value.matchingNumber == matchingNumber)
                .filter(value -> !value.hasBonusNumber || hasBonusNumber)
                .findAny()
                .orElse(NO_PRIZE);
    }

    public int getPrize() {
        return prize;
    }

    @Override
    public String toString() {
        if (hasBonusNumber) {
            return String.format("%d개 일치, 보너스 볼 일치 (%,d원)", matchingNumber, prize);
        }
        return String.format("%d개 일치 (%,d원)", matchingNumber, prize);
    }
}
