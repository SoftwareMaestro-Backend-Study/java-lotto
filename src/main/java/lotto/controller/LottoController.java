package lotto.controller;

import lotto.domain.LottoMoney;
import lotto.domain.Lottos;

import static lotto.view.Input.insertLottoMoney;
import static lotto.view.Input.insertManualLottoCount;

public class LottoController {
    public void start() {
        try {
            LottoMoney money = new LottoMoney(insertLottoMoney());
            Lottos lottos = createLottos(money);
        } catch (IllegalArgumentException error) {
        }
    }

    private Lottos createLottos(LottoMoney money) {
        int manualLottoCount = insertManualLottoCount();
    }
}
