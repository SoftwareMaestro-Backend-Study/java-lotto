package lotto.domain;

import lotto.util.Convertor;

public class BonusNumber {

    private final int number;

    private BonusNumber(int number) {
        this.number = number;
    }

    public static BonusNumber from(Lotto lotto, String input) {
        int number = Convertor.toInteger(input);
        lotto.validateRange(number);
        lotto.validateDuplication(number);
        return new BonusNumber(number);
    }

    public int getNumber() {
        return number;
    }
}
