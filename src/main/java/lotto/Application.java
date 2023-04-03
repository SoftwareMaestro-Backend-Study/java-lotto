package lotto;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoRank;
import lotto.domain.LottoRanks;
import lotto.domain.Money;
import lotto.domain.Ticket;
import lotto.domain.WinningLotto;
import lotto.domain.lottocreator.AutoLottoCreator;
import lotto.domain.lottocreator.ManualLottoCreator;
import lotto.domain.picker.RandomPicker;
import lotto.ui.Input;
import lotto.ui.Output;

public class Application {
    public static void main(String[] args) {
        final Money money = new Money(Input.getMoneyValue());
        final int manualLottoCount = Input.getManualLottoCount();
        validateLottoCount(money, manualLottoCount);

        final List<List<Integer>> manualLottos = Input.getManualLottos(manualLottoCount);
        final Ticket manualTicket = getManualTicket(manualLottos);
        final int autoLottoCount = money.remainCount(manualLottoCount);
        final Ticket autoTicket = Ticket.of(new AutoLottoCreator(new RandomPicker()), autoLottoCount);

        Output.printTicket(manualLottoCount, manualTicket, autoLottoCount, autoTicket);

        final Lotto lotto = Lotto.create(new ManualLottoCreator(Input.getWinningLotto()));
        final LottoNumber bonusBall = LottoNumber.from(Input.getBonusBall());
        WinningLotto winningLotto = new WinningLotto(lotto, bonusBall);

        final List<LottoRank> ticketResults = manualTicket.getResults(winningLotto);
        ticketResults.addAll(autoTicket.getResults(winningLotto));
        LottoRanks ranks = LottoRanks.from(ticketResults);

        Output.printResult(money, ranks);
    }

    private static void validateLottoCount(Money money, int manualLottoCount) {
        if (!money.canBuy(manualLottoCount)) {
            throw new IllegalArgumentException(String.format("[ERROR] 로또를 %d개 구매할 수 없습니다.", manualLottoCount));
        }
    }

    private static Ticket getManualTicket(List<List<Integer>> manualLottos) {
        return new Ticket(
                manualLottos.stream()
                        .map(lotto -> Lotto.create(new ManualLottoCreator(lotto)))
                        .collect(Collectors.toList())
        );
    }
}
