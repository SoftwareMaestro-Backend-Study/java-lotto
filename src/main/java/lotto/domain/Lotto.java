package lotto.domain;

import lotto.domain.generator.LottoGenerator;
import lotto.domain.result.LottoResult;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lotto {

    private static final int THE_NUMBER_OF_LOTTO = 6;
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateDuplication(numbers);
        this.numbers = new ArrayList<>(numbers);
    }

    public static Lotto from(LottoGenerator lottoGenerator) {
        return new Lotto(lottoGenerator.issue(MIN_VALUE, MAX_VALUE, THE_NUMBER_OF_LOTTO));
    }

    public void validateDuplication(int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(String.format("[ERROR] %d는 당첨 번호와 중복됩니다.", bonusNumber));
        }
    }

    public void validateRange(int number) {
        if (isNotInRange(number)) {
            throw new IllegalArgumentException(String.format("[ERROR] 로또 번호는 %d부터 %d 사이의 숫자여야 합니다.", MIN_VALUE, MAX_VALUE));
        }
    }

    public LottoResult getResult(Lotto other, int bonusNumber) {
        return LottoResult.find(getMatchingNumber(other), numbers.contains(bonusNumber));
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != THE_NUMBER_OF_LOTTO) {
            throw new IllegalArgumentException(String.format("[ERROR] 로또 번호의 개수가 %d개가 아닙니다.", THE_NUMBER_OF_LOTTO));
        }
    }

    private void validateRange(List<Integer> numbers) {
        if (hasNumberOutOfRange(numbers)) {
            throw new IllegalArgumentException(String.format("[ERROR] 로또 번호는 %d부터 %d 사이의 숫자여야 합니다.", MIN_VALUE, MAX_VALUE));
        }
    }

    private boolean hasNumberOutOfRange(List<Integer> numbers) {
        return numbers.stream().anyMatch(this::isNotInRange);
    }

    private boolean isNotInRange(int number) {
        return number < MIN_VALUE || number > MAX_VALUE;
    }

    private void validateDuplication(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != THE_NUMBER_OF_LOTTO) {
            throw new IllegalArgumentException("중복되는 번호가 포함되어 있습니다.");
        }
    }

    private int getMatchingNumber(Lotto other) {
        return (int) numbers.stream()
                .filter(other.numbers::contains)
                .count();
    }
}
