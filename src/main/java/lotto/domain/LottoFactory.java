package lotto.domain;

import java.util.List;

public class LottoFactory {

    public Lotto createManualLotto(List<Integer> numbers) {
        return new Lotto(numbers);
    }

    public Lotto createAutoLotto() {
        return new Lotto();
    }
}
