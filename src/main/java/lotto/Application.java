package lotto;

import lotto.service.LottoManager;
import lotto.service.Manager;

public class Application {

    public static void main(String[] args) {
        Manager manager = new LottoManager();
        manager.run();
    }
}
