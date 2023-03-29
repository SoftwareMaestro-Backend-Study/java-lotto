package lotto.domain.result;

import java.util.Arrays;

public enum LottoResult {

    FIRST_PRIZE(6, 0, 2000000000, "6개 일치"),
    SECOND_PRIZE(5, 1, 30000000, "5개 일치, 보너스 볼 일치"),
    THIRD_PRIZE(5, 0, 1500000, "5개 일치"),
    FOURTH_PRIZE(4, 0, 50000, "4개 일치"),
    FIFTH_PRIZE(3, 0, 5000, "3개 일치"),
    NO_PRIZE(0, 0, 0, "");

    private final int matchingNumber;
    private final int matchingNumberWithBonusNumber;
    private final int prize;
    private final String message;

    LottoResult(int matchingNumber, int matchingNumberWithBonusNumber, int prize, String message) {
        this.matchingNumber = matchingNumber;
        this.matchingNumberWithBonusNumber = matchingNumberWithBonusNumber;
        this.prize = prize;
        this.message = message;
    }

    public static LottoResult find(int matchingNumber, int matchingNumberWithBonusNumber) {
        return Arrays.stream(values())
                .filter(value -> value.matchingNumber == matchingNumber && value.matchingNumberWithBonusNumber <= matchingNumberWithBonusNumber)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 로또에 대한 결과가 없습니다."));
    }

    public int getMatchingNumber() {
        return matchingNumber;
    }

    public int getPrize() {
        return prize;
    }

    public String getMessage() {
        return message;
    }
}
