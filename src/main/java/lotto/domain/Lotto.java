package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final int MINIMUM_LOTTO_NUMBER = 1;
    private final int MAXIMUM_LOTTO_NUMBER = 45;
    private final int LOTTO_TOTAL_COUNT = 6;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public Lotto() {
        this.numbers = Randoms.pickUniqueNumbersInRange(MINIMUM_LOTTO_NUMBER, MAXIMUM_LOTTO_NUMBER, LOTTO_TOTAL_COUNT)
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Integer> getNumbers() {
        return this.numbers;
    }

    public boolean checkContainWinningNumber(Integer winningNumber) {
        return numbers.contains(winningNumber);
    }
}
