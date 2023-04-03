package lotto.lotto.lottoCreator;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.lotto.Lotto;

import java.util.Comparator;
import java.util.List;

public class RandomLottoCreator implements LottoCreator{
    private static final LottoCreator instance = new RandomLottoCreator();

    private RandomLottoCreator() {
    }

    public static LottoCreator from() {
        return instance;
    }

    @Override
    public Lotto getLotto(int min, int max, int num){
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(min,max,num);
        numbers.sort(Comparator.naturalOrder());
        return new Lotto(numbers);
    }
}
