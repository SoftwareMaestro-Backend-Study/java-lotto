package lotto.domain;

public class WinNumbers {

    private final Lotto lotto;

    private final int bonusBall;

    public WinNumbers(Lotto lotto, int bonusBall) {
        validateBonusBall(lotto, bonusBall);
        this.lotto = lotto;
        this.bonusBall = bonusBall;
    }

    private void validateBonusBall(Lotto lotto, int bonusBall) {
        if (!lotto.isPossible(bonusBall)) {
            throw new IllegalArgumentException(String.format("[ERROR] %d는 보너스 볼이 될 수 없습니다.", bonusBall));
        }
    }

    public boolean isFirstRank(Lotto other) {
        return this.lotto.isFirstRank(other);
    }

    public boolean isSecondRank(Lotto other) {
        return this.lotto.isThirdRank(other) && other.contain(this.bonusBall);
    }

    public boolean isThirdRank(Lotto other) {
        return this.lotto.isThirdRank(other) && !other.contain(this.bonusBall);
    }

    public boolean isFourthRank(Lotto other) {
        return this.lotto.isFourthRank(other);
    }

    public boolean isFifthRank(Lotto other) {
        return this.lotto.isFifthRank(other);
    }

    public boolean isOutOfRank(Lotto other) {
        return this.lotto.isOutOfRank(other);
    }
}
