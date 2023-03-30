package lotto.util;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.config.LottoConfig;

import java.util.List;

public class LottoUtil {

    private LottoUtil() {
    }

    public static List<Integer> createLotto() {
        return Randoms.pickUniqueNumbersInRange(
                LottoConfig.LOTTO_MIN_NUM,
                LottoConfig.LOTTO_MAX_NUM,
                LottoConfig.LOTTO_NUM_LENGTH
        );
    }

}
