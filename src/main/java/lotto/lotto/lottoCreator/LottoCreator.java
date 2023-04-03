package lotto.lotto.lottoCreator;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.lotto.Lotto;

public interface LottoCreator {
    public Lotto getLotto(int min, int max, int num);
}
