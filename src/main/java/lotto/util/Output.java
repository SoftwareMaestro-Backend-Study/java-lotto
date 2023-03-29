package lotto.util;

import lotto.domain.Lotto;
import lotto.domain.Lottos;

public class Output {

    private Output() {
    }

    public static void printIssuedLotto(Lottos issuedLotto) {
        System.out.printf("\n%d개를 구매했습니다.%n", issuedLotto.getQuantity());
        issuedLotto.getLottos()
                .stream()
                .map(Lotto::getNumbers)
                .map(String::valueOf)
                .forEach(System.out::println);
    }

    public static void printWinningDetail(String winningDetail, double profitRate) {
        System.out.printf("\n당첨 통계\n---\n%s\n총 수익률은 %.1f%%입니다.\n", winningDetail, profitRate);
    }
}
