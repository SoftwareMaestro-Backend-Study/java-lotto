package lotto.domain.rankvalidator;

import lotto.domain.Lotto;
import lotto.domain.WinNumbers;

public interface RankValidator {

    boolean isRanked(WinNumbers winNumbers, Lotto lotto);
}
