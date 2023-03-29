package lotto.lotto;

import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final List<Integer> numbers;
    private static final int LENGTH = 6;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LENGTH) {
            throw new IllegalArgumentException();
        }
    }

    // TODO: 추가 기능 구현

    public Prize comparePrize(Lotto winningLotto, int bonus) {
        return null;
    }

    @Override
    public String toString() {
        return "[" +
                numbers.stream().map(String::valueOf).collect(Collectors.joining(",")) +
                "]";
    }
}
