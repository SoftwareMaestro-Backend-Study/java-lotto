package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private List<Lotto> lottos;
    private Integer manualLottoCount;
    private Integer autoLottoCount;

    public Lottos(List<Lotto> manualLottos, List<Lotto> autoLottos) {
        List<Lotto> lottos = new ArrayList<>();
        lottos.addAll(manualLottos);
        lottos.addAll(autoLottos);

        this.lottos = lottos;
        this.manualLottoCount = manualLottos.size();
        this.autoLottoCount = autoLottos.size();
    }
}
