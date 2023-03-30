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

    public int calculateSameLottoNumber(Lotto lotto) {
        return this.lotto.calculateSameLottoNumber(lotto);
    }

    public boolean matchBonus(Lotto lotto) {
        return lotto.contain(bonusBall);
    }
}
