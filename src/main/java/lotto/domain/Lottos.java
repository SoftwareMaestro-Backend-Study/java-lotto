package lotto.domain;

import lotto.domain.generator.LottoGenerator;
import lotto.domain.result.LottoResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public static Lottos from(LottoGenerator lottoGenerator, PurchasingMoney purchasingMoney) {
        return new Lottos(Stream.generate(() -> Lotto.from(lottoGenerator))
                .limit(purchasingMoney.getQuantity())
                .collect(Collectors.toList()));
    }

    public List<LottoResult> compare(List<Integer> winningLotto, BonusNumber bonusNumber) {
        return lottos.stream()
                .map(lotto -> lotto.getResult(winningLotto, bonusNumber))
                .collect(Collectors.toList());
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int getQuantity() {
        return lottos.size();
    }
}
