package lotto.domain;

import java.util.ArrayList;
import java.util.List;

import static lotto.view.Input.insertManualLottoCount;
import static lotto.view.Input.insertManualLottoNumbers;

public class LottoMachine {
    private final LottoFactory lottoFactory = new LottoFactory();
    public Lottos createLottos(LottoMoney money, int manualLottoCount) {
        List<Lotto> manualLottos = createManualLottos(manualLottoCount);
        List<Lotto> autoLottos = createAutoLottos(money.getTotalLottoCount() - manualLottos.size());


    }

    private List<Lotto> createManualLottos(int manualLottoCount) {
        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < manualLottoCount; i++){
            List<Integer> numbers = insertManualLottoNumbers();
            Lotto manualLotto = lottoFactory.createManualLotto(numbers);
            manualLottos.add(manualLotto);
        }

        return manualLottos;
    }

    private List<Lotto> createAutoLottos(int autoLottoCount) {
        List<Lotto> autoLottos = new ArrayList<>();
        for (int i = 0; i < autoLottoCount; i++) {
            Lotto autoLotto = lottoFactory.createAutoLotto();
            autoLottos.add(autoLotto);
        }

        return autoLottos;
    }

}
