package lotto.domain;

public class WinningLotto {

    public final Lotto lotto;
    private final LottoNumber bonusBall;

    public WinningLotto(Lotto lotto, LottoNumber bonusBall) {
        validateBonusBall(lotto, bonusBall);

        this.lotto = lotto;
        this.bonusBall = bonusBall;
    }

    private static void validateBonusBall(Lotto winningLotto, LottoNumber bonusBall) {
        if (winningLotto.contains(bonusBall)) {
            throw new IllegalArgumentException(String.format("[ERROR] 로또에 이미 %s가 존재합니다.", bonusBall));
        }
    }

    public int calculateSameLottoNumber(Lotto lotto) {
        return this.lotto.calculateSameLottoNumber(lotto);
    }

    public boolean matchBonus(Lotto lotto) {
        return lotto.contains(this.bonusBall);
    }
}
