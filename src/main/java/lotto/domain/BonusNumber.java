package lotto.domain;

public class BonusNumber {

    private final int number;

    private BonusNumber(int number) {
        this.number = number;
    }

    public static BonusNumber from(Lotto lotto, int number) {
        validateRange(lotto, number);
        validateDuplication(lotto, number);
        return new BonusNumber(number);
    }

    private static void validateRange(Lotto lotto, int number) {
        if (lotto.isNotInRange(number)) {
            throw new IllegalArgumentException(String.format("[ERROR] %d는 범위를 벗어난 숫자입니다.", number));
        }

    }

    private static void validateDuplication(Lotto lotto, int number) {
        if (lotto.has(number)) {
            throw new IllegalArgumentException(String.format("[ERROR] %d는 당첨 번호와 중복됩니다.", number));
        }
    }

    public int getNumber() {
        return number;
    }
}
