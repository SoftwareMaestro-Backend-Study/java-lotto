package lotto.lotto.lottoCreator;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.lotto.Lotto;

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
        return new Lotto(numbers);
    }
}
