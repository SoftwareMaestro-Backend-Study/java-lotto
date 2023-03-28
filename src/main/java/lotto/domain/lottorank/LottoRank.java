package lotto.domain.lottorank;

import java.util.Arrays;
import lotto.domain.Lotto;
import lotto.domain.WinNumbers;
import lotto.domain.rankvalidator.RankValidator;

public enum LottoRank {
    FIRST(2_000_000_000, WinNumbers::isFirstRank),
    SECOND(30_000_000, WinNumbers::isSecondRank),
    THIRD(1_500_000, WinNumbers::isThirdRank),
    FOURTH(50_000, WinNumbers::isFourthRank),
    FIFTH(5_000, WinNumbers::isFifthRank),
    OTHER(0, WinNumbers::isOutOfRank);


    private final int prize;
    private final RankValidator rankValidator;

    LottoRank(int prize, RankValidator rankValidator) {
        this.prize = prize;
        this.rankValidator = rankValidator;
    }

    public static LottoRank calculateRank(WinNumbers winNumbers, Lotto lotto) {
        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank.rankValidator.isRanked(winNumbers, lotto))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 등수가 존재하지 않습니다"));
    }

    public int getPrize() {
        return prize;
    }
}
