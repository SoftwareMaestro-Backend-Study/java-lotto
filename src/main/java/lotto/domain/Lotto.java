package lotto.domain;

import java.util.Set;
import lotto.domain.lottocreator.LottoCreator;

public class Lotto {

    private static final int LOTTO_SIZE = 6;

    private final Set<LottoNumber> numbers;

    private Lotto(Set<LottoNumber> numbers) {
        validateLength(numbers);
        this.numbers = numbers;
    }

    public static Lotto create(LottoCreator creator) {
        return new Lotto(creator.create(LOTTO_SIZE));
    }

    private void validateLength(Set<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(String.format("[ERROR] 로또 숫자 개수는 %d일 수 없습니다.", numbers.size()));
        }
    }

    public int calculateSameLottoNumber(Lotto other) {
        return (int) other.numbers.stream()
                .filter(this::contains)
                .count();
    }

    public boolean contains(LottoNumber bonusBall) {
        return this.numbers.contains(bonusBall);
    }
}
