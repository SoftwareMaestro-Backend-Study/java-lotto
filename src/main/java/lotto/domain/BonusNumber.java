package lotto.domain;

public class BonusNumber {

    private final int number;

    private BonusNumber(int number) {
        this.number = number;
    }

    public static BonusNumber from(Lotto lotto, int number) {
        lotto.validateRange(number);
        lotto.validateDuplication(number);
        return new BonusNumber(number);
    }

    public int getNumber() {
        return number;
    }
}
