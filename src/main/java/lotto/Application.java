package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoMachine;

public class Application {
    public static void main(String[] args) {
        LottoMachine lottoMachine = new LottoMachine();
        LottoController lottoController = new LottoController(lottoMachine);

        lottoController.start();
    }
}
