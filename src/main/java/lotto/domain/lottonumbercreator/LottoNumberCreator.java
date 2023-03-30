package lotto.domain.lottonumbercreator;

import java.util.List;

public interface LottoNumberCreator {
    List<Integer> create(int minLottoNumber, int maxLottoNumber, int lottoNumbersSize);
}
