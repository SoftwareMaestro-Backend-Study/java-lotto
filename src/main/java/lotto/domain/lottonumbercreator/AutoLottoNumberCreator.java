package lotto.domain.lottonumbercreator;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class AutoLottoNumberCreator implements LottoNumberCreator{
    @Override
    public List<Integer> create(int minLottoNumber, int maxLottoNumber, int lottoNumbersSize) {
        return Randoms.pickUniqueNumbersInRange(minLottoNumber, maxLottoNumber, lottoNumbersSize);
    }
}
