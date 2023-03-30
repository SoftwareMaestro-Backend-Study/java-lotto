package lotto.lotto;

import lotto.config.LottoConfig;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);

        try {
            numbers.sort(Comparator.naturalOrder());
        } catch (UnsupportedOperationException ignored) {}

        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LottoConfig.LOTTO_NUM_LENGTH)
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

    @Override
    public String toString() {
        return "[" +
                numbers.stream().map(String::valueOf).collect(Collectors.joining(", ")) +
                "]";
    }

}
