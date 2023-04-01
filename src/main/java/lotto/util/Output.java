package lotto.util;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.result.ProfitRate;
import lotto.domain.result.WinningDetail;

public class Output {

    private Output() {
    }

    public static void printIssuedLotto(Lottos issuedLotto, int quantity) {
        System.out.printf("\n%d개를 구매했습니다.%n", quantity);
        issuedLotto.getLottos()
                .stream()
                .map(Lotto::getNumbers)
                .map(String::valueOf)
                .forEach(System.out::println);
    }

    public static void printWinningDetail(WinningDetail winningDetail, ProfitRate profitRate) {
        System.out.println("\n당첨 통계\n---\n" +
                winningDetail +
                String.format("\n총 수익률은 %s%%입니다.\n", profitRate));
    }
}
