package lotto.domain.lottocreator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;

public class ManualLottoCreator implements LottoCreator {

    private final List<Integer> numbers;

    public ManualLottoCreator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Set<LottoNumber> create(int lottoSize) {
        validateSize(lottoSize);

        return this.numbers.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toSet());
    }

    private void validateSize(int lottoSize) {
        if (this.numbers.size() != lottoSize) {
            throw new IllegalArgumentException(String.format("[ERROR] 로또 숫자의 갯수가 %d가 아닙니다.", lottoSize));
        }
    }
}
