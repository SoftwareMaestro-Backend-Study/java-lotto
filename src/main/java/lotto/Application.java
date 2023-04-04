package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoFactory;
import lotto.domain.LottoMachine;

public class Application {
    public static void main(String[] args) {
        LottoFactory lottoFactory = new LottoFactory();
        LottoMachine lottoMachine = new LottoMachine(lottoFactory);
        LottoController lottoController = new LottoController(lottoMachine);

        lottoController.start();
    }
}
