package lotto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.WinNumbers;
import lotto.domain.lottonumbercreator.AutoLottoNumberCreator;
import lotto.domain.lottonumbercreator.LottoNumberCreator;
import lotto.domain.lottorank.LottoRank;
import lotto.domain.lottorank.LottoRanks;
import lotto.view.InputView;

public class Application {
    private static final LottoNumberCreator CREATOR = new AutoLottoNumberCreator();

    public static void main(String[] args) {
        System.out.println("구입금액을 입력해 주세요.");

        final Money money = new Money(InputView.requestInteger());
        final int lottoCount = money.getLottoCount();
        System.out.println(String.format("\n%d개를 구매했습니다.", lottoCount));

        final List<Lotto> autoLottos = getAutoLottos(lottoCount);
        autoLottos.forEach(System.out::println);

        System.out.println("\n당첨 번호를 입력해 주세요.");
        final Lotto winLotto = new Lotto(InputView.requestWinNumbers());

        System.out.println("\n보너스 번호를 입력해 주세요.");
        int bonusBall = InputView.requestInteger();

        final WinNumbers winNumbers = new WinNumbers(winLotto, bonusBall);
        final LottoRanks lottoRanks = getLottoRanks(autoLottos, winNumbers);

        System.out.println("당첨 통계\n---");
        System.out.println(lottoRanks);
        System.out.println(String.format("총 수익률은 %.1f%%입니다.", money.getRateOfProfit(lottoRanks.getProfit())));
    }

    private static List<Lotto> getAutoLottos(int lottoCount) {
        return Stream.generate(() -> Lotto.from(CREATOR))
                .limit(lottoCount)
                .collect(Collectors.toList());
    }

    private static LottoRanks getLottoRanks(List<Lotto> autoLottos, WinNumbers winNumbers) {
        final List<LottoRank> ranks = autoLottos.stream()
                .map(lotto -> LottoRank.calculateRank(winNumbers, lotto))
                .collect(Collectors.toList());

        return LottoRanks.from(ranks);
    }
}
