package lotto.lotto;


import lotto.lotto.lottoCreator.LottoCreator;
import lotto.lotto.lottoCreator.RandomLottoCreator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final static int LOTTO_NUM_LENGTH = 6;
    private final static int LOTTO_MIN_NUM = 1;
    private final static int LOTTO_MAX_NUM = 45;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);

        try {
            numbers.sort(Comparator.naturalOrder());
        } catch (UnsupportedOperationException ignored) {
        }

        this.numbers = numbers;
    }

    public static Lotto from(LottoCreator lottoCreator) {
        return lottoCreator.getLotto(
                LOTTO_MIN_NUM,
                LOTTO_MAX_NUM,
                LOTTO_NUM_LENGTH
        );
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUM_LENGTH)
            throw new IllegalArgumentException("[ERROR] 숫자의 개수가 올바르지 않습니다.");
        if (numbers.size() != (new HashSet<>(numbers)).size())
            throw new IllegalArgumentException("[ERROR] 숫자에 중복이 없어야 합니다.");
    }

    public Prize comparePrize(Lotto winningLotto, int bonus) {
        List<Integer> list = new ArrayList<>();
        list.addAll(numbers);
        list.addAll(winningLotto.numbers);
        int matches = list.size() - (new HashSet<>(list)).size();
        boolean isBonus = numbers.contains(bonus);

        Prize prize = Prize.NONE;
        for (Prize p : Prize.values()) {
            if (
                    p.isPrized() &&
                            p.getMatchNum() <= matches &&
                            p.getGrade() <= prize.getGrade()
            ) {
                if (p.isBonus() && !isBonus) // 보너스 true인 경우 isBonus 확인한다.
                    continue;
                prize = p;
            }
        }
        return prize;
    }

    public boolean contains(int num) {
        return numbers.contains(num);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
