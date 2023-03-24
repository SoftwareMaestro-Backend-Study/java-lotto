package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private static final int LOTTO_NUMBERS_SIZE = 6;
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> values) {
        final List<LottoNumber> numbers = values.stream()
                .map(LottoNumber::from)
                .distinct()
                .collect(Collectors.toList());
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(String.format("[ERROR] 로또 숫자의 개수는 %d일 수 없습니다.", numbers.size()));
        }
    }

    // TODO: 추가 기능 구현
}
