package lotto.domain;

public class WinNumbers {

    private final Lotto lotto;

    private final Integer bonusBall;

    public WinNumbers(Lotto lotto, Integer bonusBall) {
        validateBonusBall(lotto, bonusBall);
        this.lotto = lotto;
        this.bonusBall = bonusBall;
    }

    private void validateBonusBall(Lotto lotto, Integer bonusBall) {
        if (!lotto.isPossible(bonusBall)) {
            throw new IllegalArgumentException(String.format("[ERROR] %d는 보너스 볼이 될 수 없습니다.", bonusBall));
        }
    }
}
