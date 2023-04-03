package lotto.controller;

import lotto.domain.LottoMachine;
import lotto.domain.LottoMoney;
import lotto.domain.Lottos;

import static lotto.view.Input.insertLottoMoney;
import static lotto.view.Input.insertManualLottoCount;

public class LottoController {

    private final LottoMachine lottoMachine = new LottoMachine();

    public void start() {
        try {
            LottoMoney money = new LottoMoney(insertLottoMoney());
            Lottos lottos = lottoMachine.createLottos(money, insertManualLottoCount());
        } catch (IllegalArgumentException error) {
        }
    }

}
