package lotto.domain.lottocreator;

import java.util.Set;
import lotto.domain.LottoNumber;

public interface LottoCreator {
    Set<LottoNumber> create(int lottoSize);
}
