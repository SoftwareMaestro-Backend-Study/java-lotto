package lotto.domain.generator;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;
import java.util.stream.Collectors;

public class IssuedLottoGenerator implements LottoGenerator {

    private static final LottoGenerator ISSUED_LOTTO_GENERATOR = new IssuedLottoGenerator();

    private IssuedLottoGenerator() {
    }

    public static LottoGenerator create() {
        return ISSUED_LOTTO_GENERATOR;
    }

    @Override
    public List<Integer> issue(int minValue, int maxValue, int quantity) {
        return Randoms.pickUniqueNumbersInRange(minValue, maxValue, quantity)
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }
}