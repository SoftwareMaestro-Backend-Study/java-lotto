package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.domain.lottocreator.LottoCreator;

public class Ticket {
    private final List<Lotto> lottos;

    public Ticket(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Ticket of(LottoCreator creator, int count) {
        final List<Lotto> lottos = Stream.generate(() -> Lotto.create(creator))
                .limit(count)
                .collect(Collectors.toList());

        return new Ticket(lottos);
    }
}
