package lotto.lotto;

public class Bonus {
    private final int bonusNumber;

    private Bonus(int bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    public static Bonus from(int num, Lotto lotto) {
        lotto.validateBonusNumber(num);
        return new Bonus(num);
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
