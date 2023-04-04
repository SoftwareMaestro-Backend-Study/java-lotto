package lotto.domain;

import lotto.domain.enumeration.NumberType;
import lotto.domain.enumeration.Ranking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.domain.enumeration.Ranking.*;
import static lotto.view.Input.insertLottoNumbers;
import static lotto.view.Output.printInsertManualLottoNumbersRequest;

public class LottoMachine {
    private final LottoFactory lottoFactory = new LottoFactory();

    public Lottos createLottos(LottoMoney money, int manualLottoCount) {
        List<Lotto> manualLottos = createManualLottos(manualLottoCount);
        List<Lotto> autoLottos = createAutoLottos(money.getTotalLottoCount() - manualLottos.size());

        return new Lottos(manualLottos, autoLottos);
    }

    private List<Lotto> createManualLottos(int manualLottoCount) {
        List<Lotto> manualLottos = new ArrayList<>();
        printInsertManualLottoNumbersRequest();
        for (int i = 0; i < manualLottoCount; i++) {
            List<Integer> numbers = insertLottoNumbers();
            Lotto manualLotto = lottoFactory.createManualLotto(numbers);
            manualLottos.add(manualLotto);
        }

        return manualLottos;
    }

    private List<Lotto> createAutoLottos(int autoLottoCount) {
        List<Lotto> autoLottos = new ArrayList<>();
        for (int i = 0; i < autoLottoCount; i++) {
            Lotto autoLotto = lottoFactory.createAutoLotto();
            autoLottos.add(autoLotto);
        }

        return autoLottos;
    }

    public List<WinningNumber> createWinningNumbers(List<Integer> normalNumbers, Integer bonusNumber) {
        List<WinningNumber> totalWinningNumbers = new ArrayList<>();
        List<WinningNumber> normalWinningNumbers = createNormalWinningNumbers(normalNumbers);
        WinningNumber bonusWinningNumber = createBonusNumber(bonusNumber);

        totalWinningNumbers.addAll(normalWinningNumbers);
        totalWinningNumbers.add(bonusWinningNumber);

        return totalWinningNumbers;
    }

    private List<WinningNumber> createNormalWinningNumbers(List<Integer> normalNumbers) {
        List<WinningNumber> result = new ArrayList<>();
        for (Integer number : normalNumbers) {
            WinningNumber normalNumber = new WinningNumber(number, NumberType.NORMAL);
            result.add(normalNumber);
        }
        return result;
    }

    private WinningNumber createBonusNumber(Integer bonusNumber) {
        return new WinningNumber(bonusNumber, NumberType.BOUNS);
    }

    public LottoResult computeLottoResult(Lottos lottos, List<WinningNumber> winningNumbers) {
        Map<Ranking, Integer> winningInfo = new HashMap<>();

        for (Lotto lotto : lottos.getLottos()) {
            Ranking ranking = determineRanking(lotto, winningNumbers);
            winningInfo.put(ranking, winningInfo.getOrDefault(ranking, 0) + 1);
        }
        return new LottoResult(winningInfo);
    }

    private Ranking determineRanking(Lotto lotto, List<WinningNumber> winningNumbers) {
        int equalNormalNumberCount = getEqualCount(lotto, winningNumbers);
        boolean isBonusEqual = checkBonusEqual(lotto, winningNumbers);

        return Ranking.create(equalNormalNumberCount, isBonusEqual);
    }

    private int getEqualCount(Lotto lotto, List<WinningNumber> winningNumbers) {
        return (int) winningNumbers.stream()
                .map(WinningNumber::getNumber)
                .filter(lotto::checkContainWinningNumber)
                .count();
    }

    private boolean checkBonusEqual(Lotto lotto, List<WinningNumber> winningNumbers) {
        return lotto.getNumbers()
                .contains(
                        winningNumbers.stream()
                                .filter(WinningNumber::isBonus)
                                .map(WinningNumber::getNumber)
                                .findFirst().get()
                );
    }

    public static Double calculateProfit(LottoResult lottoResult, LottoMoney money) {
        Map<Ranking, Integer> winningInfo = lottoResult.getWinningInfo();
        Long value = sum(winningInfo);

        return (double) value / (double) money.getPrice();
    }

    private static Long sum(Map<Ranking, Integer> winningInfo) {
        return winningInfo.getOrDefault(FIFTH,0) * FIFTH.getPrizeMoney()
                + winningInfo.getOrDefault(FORTH, 0) * FORTH.getPrizeMoney()
                + winningInfo.getOrDefault(THIRD, 0) * THIRD.getPrizeMoney()
                + winningInfo.getOrDefault(SECOND, 0) * SECOND.getPrizeMoney()
                + winningInfo.getOrDefault(FIRST, 0) * FIRST.getPrizeMoney();
    }
}
