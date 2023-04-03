package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Lottos;

import java.util.List;

public class Output {
    public static void printErrorMessage(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }

    public static void printLottos(Lottos lottos) {
        printLottosCount(lottos);
        printLottoNumbers(lottos);
    }

    private static void printLottosCount(Lottos lottos) {
        int manualLottoCount = lottos.getManualLottoCount();
        int autoLottoCount = lottos.getAutoLottoCount();
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.", manualLottoCount, autoLottoCount);
    }

    private static void printLottoNumbers(Lottos lottos) {
        List<Lotto> lottoList = lottos.getLottos();

        lottoList.stream()
                .map(Lotto::getNumbers)
                .forEach(System.out::println);
    }
}
