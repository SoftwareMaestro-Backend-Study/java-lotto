package lotto.controller;

import lotto.domain.*;

import java.util.List;

import static lotto.view.Input.*;
import static lotto.view.Output.*;

public class LottoController {

    private final LottoMachine lottoMachine;

    public LottoController(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public void start() {
        try {
            LottoMoney money = new LottoMoney(insertLottoMoney());
            Lottos lottos = lottoMachine.createLottos(money, insertManualLottoCount());
            printLottos(lottos);
            List<WinningNumber> winningNumbers = lottoMachine.createWinningNumbers(insertNormalWinningNumbers(), insertBonusWinningNumber());
            LottoResult lottoResult = lottoMachine.computeLottoResult(lottos, winningNumbers);
            printLottoResult(lottoResult, money);
        } catch (IllegalArgumentException error) {
            printErrorMessage(error.getMessage());
        }
    }

}
