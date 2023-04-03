package lotto.lotto.lottoCreator;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.lotto.Lotto;

import java.util.HashSet;
import java.util.List;

public class ManualLottoCreator implements LottoCreator {
    private List<Integer> numbers;

    private ManualLottoCreator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static ManualLottoCreator from(List<Integer> numbers) {
        return new ManualLottoCreator(numbers);
    }

    @Override
    public Lotto getLotto(int min, int max, int num) {
        validateLength(num);
        validateDuplication();
        validateNumRange(min,max);
        return new Lotto(numbers);
    }

    private void validateLength(int length) {
        if (numbers.size() != length)
            throw new IllegalArgumentException("[ERROR] 숫자의 개수가 올바르지 않습니다.");
    }

    private void validateDuplication() {
        if (numbers.size() != (new HashSet<>(numbers)).size())
            throw new IllegalArgumentException("[ERROR] 숫자에 중복이 없어야 합니다.");
    }

    private void validateNumRange(int min, int max) {
        for (int n : numbers)
            if (n < min || n > max)
                throw new IllegalArgumentException("[ERROR] 올바른 범위 내의 숫자를 입력해주세요.");
    }
}
