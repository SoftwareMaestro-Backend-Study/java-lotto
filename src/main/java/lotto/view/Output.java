package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoMoney;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.enumeration.Ranking;

import java.util.List;
import java.text.NumberFormat;
import java.util.Map;

import static lotto.domain.LottoMachine.calculateProfit;
import static lotto.domain.enumeration.Ranking.*;

public class Output {
    private static final String MANUAL_LOTTO_NUMBERS_REQUEST = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String RESULT_ANNOUNCEMENT = "당첨 통계";
    private static final String RESULT_ANNOUNCEMENT_LINE = "---------";

    private static final NumberFormat numberFormat = NumberFormat.getInstance();

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

    public static void printLottoResult(LottoResult lottoResult, LottoMoney money) {
        printLottoResultStartFormat();
        printLottoWinningResult(lottoResult);
        printProfit(lottoResult, money);
    }

    private static void printProfit(LottoResult lottoResult, LottoMoney money) {
        Double profit = calculateProfit(lottoResult, money);
        System.out.printf("총 수익률은 %.2f입니다.", profit);
    }

    private static void printLottoWinningResult(LottoResult lottoResult) {
        String FIFTH_STATS = String.format("3개 일치 (%s원) - %s개", numberFormat.format(FIFTH.getPrizeMoney()), lottoResult.getWinningInfo().getOrDefault(FIFTH, 0));
        String FORTH_STATS = String.format("4개 일치 (%s원) - %s개", numberFormat.format(FORTH.getPrizeMoney()), lottoResult.getWinningInfo().getOrDefault(FORTH, 0));
        String THIRD_STATS = String.format("5개 일치 (%s원) - %s개", numberFormat.format(THIRD.getPrizeMoney()), lottoResult.getWinningInfo().getOrDefault(THIRD, 0));
        String SECOND_STATS = String.format("5개 일치, 보너스 볼 일치 (%s원) - %s개", numberFormat.format(SECOND.getPrizeMoney()), lottoResult.getWinningInfo().getOrDefault(SECOND, 0));
        String FIRST_STATS = String.format("6개 일치 (%s원) - %s개", numberFormat.format(FIRST.getPrizeMoney()), lottoResult.getWinningInfo().getOrDefault(FIRST, 0));
        System.out.println(FIFTH_STATS);
        System.out.println(FORTH_STATS);
        System.out.println(THIRD_STATS);
        System.out.println(SECOND_STATS);
        System.out.println(FIRST_STATS);
    }

    private static void printLottoResultStartFormat() {
        System.out.println(RESULT_ANNOUNCEMENT);
        System.out.println(RESULT_ANNOUNCEMENT_LINE);
    }
}
