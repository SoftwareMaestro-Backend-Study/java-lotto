package lotto.ui;

import lotto.domain.LottoRanks;
import lotto.domain.Money;
import lotto.domain.Ticket;

public class Output {

    private Output() {
        throw new UnsupportedOperationException();
    }

    public static void printTicket(int manualLottoCount, Ticket manualTicket, int autoLottoCount, Ticket autoTicket) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.%n", manualLottoCount, autoLottoCount);
        System.out.println(manualTicket);
        System.out.println(autoTicket);
    }

    public static void printResult(Money money, LottoRanks ranks) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println(ranks);
        System.out.printf("총 수익률은 %.2f입니다.", money.getProfitRate(ranks.getProfit()));
    }
}
