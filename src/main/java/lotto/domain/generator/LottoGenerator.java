package lotto.domain.generator;

import java.util.List;

public interface LottoGenerator {

    List<Integer> issue(int minValue, int maxValue, int quantity);
}
