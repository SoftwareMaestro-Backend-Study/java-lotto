package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.WinningNumber;

import java.util.List;

public class Output {
    private static final String MANUAL_LOTTO_NUMBERS_REQUEST = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String RESULT_ANNOUNCEMENT = "당첨 통계";
    private static final String RESULT_ANNOUNCEMENT_LINE = "---------";

    public static void printErrorMessage(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }

    public static void printInsertManualLottoNumbersRequest() {
        System.out.println(MANUAL_LOTTO_NUMBERS_REQUEST);
    }

    public static void printLottos(Lottos lottos) {
        printLottosCount(lottos);
        printLottoNumbers(lottos);
    }

    private static void printLottosCount(Lottos lottos) {
        int manualLottoCount = lottos.getManualLottoCount();
        int autoLottoCount = lottos.getAutoLottoCount();
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manualLottoCount, autoLottoCount);
    }

    private static void printLottoNumbers(Lottos lottos) {
        List<Lotto> lottoList = lottos.getLottos();

        lottoList.stream()
                .map(Lotto::getNumbers)
                .forEach(System.out::println);
    }

    public static void printLottoResult(LottoResult lottoResult) {
        printLottoResultStartFormat();
        printLottoWinningResult(lottoResult);
    }

    private static void printLottoWinningResult(LottoResult lottoResult) {
        // to do
    }

    private static void printLottoResultStartFormat() {
        System.out.println(RESULT_ANNOUNCEMENT);
        System.out.println(RESULT_ANNOUNCEMENT_LINE);
    }
}
