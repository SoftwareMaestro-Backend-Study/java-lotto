package lotto.domain.lottorank;

import java.util.Arrays;
import lotto.domain.Lotto;
import lotto.domain.WinNumbers;
import lotto.domain.rankvalidator.RankValidator;

public enum LottoRank {
    FIRST(2_000_000_000, 6, false),
    SECOND(30_000_000, 5,true),
    THIRD(1_500_000, 5, false),
    FOURTH(50_000, 4, false),
    FIFTH(5_000, 3, false),
    OTHER(0, 0, false);


    private final int prize;
    private final int count;
    private final boolean bonus;

    LottoRank(int prize, int count, boolean bonus) {
        this.prize = prize;
        this.count = count;
        this.bonus = bonus;
    }

    public static LottoRank calculateRank(WinNumbers winNumbers, Lotto lotto) {
        return Arrays.stream(LottoRank.values())
                .filter(rank -> rank.count == winNumbers.calculateSameLottoNumber(lotto))
                .filter(rank -> !rank.bonus || winNumbers.matchBonus(lotto))
                .findAny()
                .orElse(LottoRank.OTHER);
    }

    public int getPrize() {
        return prize;
    }
}
