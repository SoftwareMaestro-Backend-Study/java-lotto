package lotto.domain.result;

import java.util.Arrays;

public enum LottoResult {

    FIRST_PRIZE(6, false, 2000000000, "6개 일치"),
    SECOND_PRIZE(5, true, 30000000, "5개 일치, 보너스 볼 일치"),
    THIRD_PRIZE(5, false, 1500000, "5개 일치"),
    FOURTH_PRIZE(4, false, 50000, "4개 일치"),
    FIFTH_PRIZE(3, false, 5000, "3개 일치"),
    NO_PRIZE(0, false, 0, "");

    private final int matchingNumber;
    private final boolean hasBonusNumber;
    private final int prize;
    private final String message;

    LottoResult(int matchingNumber, boolean hasBonusNumber, int prize, String message) {
        this.matchingNumber = matchingNumber;
        this.hasBonusNumber = hasBonusNumber;
        this.prize = prize;
        this.message = message;
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

    public String getMessage() {
        return message;
    }
}
