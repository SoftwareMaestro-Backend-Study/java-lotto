package lotto.lotto;

import lotto.lotto.lottoCreator.LottoCreator;
import lotto.lotto.model.StatusOutputModel;
import lotto.purchase.PurchaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoBundle {
    private List<Lotto> lottos = new ArrayList<>();
    private Lotto winningLotto;
    private int bonus;

    /**
     * lottoCreators만큼 새로운 로또 생성하기
     */
    public void createLottos(List<LottoCreator> lottoCreators) {
        for (LottoCreator creator : lottoCreators) {
            Lotto lotto = Lotto.from(creator);
            lottos.add(lotto);
        }
    }

    /**
     * 당첨번호 저장하기
     */
    public void setWinningLotto(LottoCreator creator) {
        Lotto lotto = Lotto.from(creator);
        this.winningLotto = lotto;
    }

    /**
     * 보너스번호 저장하기
     */
    public void setBonus(int bonus) {
        if (winningLotto.contains(bonus)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호와 당첨 번호에 중복이 있습니다.");
        }
        this.bonus = bonus;
    }

    /**
     * 로또 출력하기
     */
    public List<List<Integer>> getLottosNumList() {
        List<List<Integer>> lottoNumbers = new ArrayList<>();
        for (Lotto lotto : lottos)
            lottoNumbers.add(lotto.getNumbers());
        return lottoNumbers;
    }

    /**
     * 당첨 통계 출력하기
     */
    public StatusOutputModel getResultStatus() {
        Map<Prize, Integer> prizeMap = getPrizeStat();
        int totalreward = getTotalReward(prizeMap);
        double rewardPercent = PurchaseService.getRewardPercent(lottos.size(), totalreward);

        StringBuilder sb = new StringBuilder();
        for (Prize prize : Prize.values()) {
            if (!prize.isPrized()) continue;
            sb.append(prize.getToString());
            sb.append(
                    String.format(" (%,d원) - %d개",
                            prize.getReward(),
                            prizeMap.get(prize)
                    ));
            sb.append("\n");
        }
        sb.append(String.format("총 수익률은 %.1f%%입니다.", rewardPercent));

        return new StatusOutputModel(sb.toString());
    }

    public Map<Prize, Integer> getPrizeStat() {
        Map<Prize, Integer> prizeMap = Prize.getInitializedMap();
        for (Lotto lotto : lottos) {
            Prize prize = lotto.comparePrize(winningLotto, bonus);
            if (prize.isPrized())
                prizeMap.put(prize, prizeMap.get(prize) + 1);
        }
        return prizeMap;
    }

    public int getTotalReward(Map<Prize, Integer> prizeMap) {
        int totalReward = 0;
        for (Prize prize : Prize.values()) {
            if (prizeMap.get(prize) != 0)
                totalReward += prize.getReward() * prizeMap.get(prize);
        }
        return totalReward;
    }

}
