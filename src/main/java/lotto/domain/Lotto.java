package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lottonumbercreator.LottoNumberCreator;

public class Lotto {
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicated(numbers);
        validateNumbersRange(numbers);
        this.numbers = numbers;
    }

    public static Lotto from(LottoNumberCreator creator) {
        return new Lotto(creator.create(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER, LOTTO_NUMBERS_SIZE));
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(String.format("[ERROR] 로또 숫자의 개수는 %d일 수 없습니다.", numbers.size()));
        }
    }

    private void validateDuplicated(List<Integer> numbers) {
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new IllegalArgumentException("[ERROR] 중복되는 숫자가 존재합니다.");
        }
    }

    private void validateNumbersRange(List<Integer> numbers) {
        if (!numbers.stream().allMatch(this::isInRange)) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] %d ~ %d 범위를 넘어가는 숫자가 존재합니다.", MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
            );
        }
    }

    private boolean isInRange(int bonusBall) {
        return bonusBall >= MIN_LOTTO_NUMBER && bonusBall <= MAX_LOTTO_NUMBER;
    }

    public boolean isPossible(int bonusBall) {
        final boolean isInRange = isInRange(bonusBall);
        final boolean isNotLottoNumber = !numbers.contains(bonusBall);

        return isInRange && isNotLottoNumber;
    }

    public int calculateSameLottoNumber(Lotto other) {
        return (int) other.numbers.stream()
                .filter(this::contain)
                .count();
    }

    public boolean contain(Integer number) {
        return this.numbers.contains(number);
    }

    @Override
    public String toString() {
        return String.format("[%s]",
                this.numbers.stream()
                .sorted()
                .map(number -> Integer.toString(number))
                .collect(Collectors.joining(", ")));
    }
}
