package lotto.lotto;

import javafx.scene.input.DataFormat;
import lotto.purchase.PurchaseService;
import lotto.util.LottoUtil;
import lotto.util.UserOutput;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class LottoBundle {
    private List<Lotto> lottos = new ArrayList<>();
    private Lotto winningLotto;
    private int bonus;


    /**
     * lottoNum의 수만큼 새로운 로또 생성하기
     */
    public void createLottos(int lottoNum) {
        for (int i = 0; i < lottoNum; i++) {
            Lotto lotto = new Lotto(LottoUtil.createLotto());
            lottos.add(lotto);
        }
    }

    /**
     * 당첨번호 입력받기
     */
    public void setWinningLotto(List<Integer> winningNumList) {
        Lotto lotto = new Lotto(winningNumList);
        this.winningLotto = lotto;
    }

    /**
     * 보너스번호 입력받기
     */
    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    /**
     * 로또 출력하기
     */
    public void printLottos() {
        StringBuilder sb = new StringBuilder();

        for (Lotto lotto : lottos)
            sb.append(lotto).append("\n");

        UserOutput.printLottos(lottos.size(), sb.toString());
    }

    /**
     * 당첨 통계 출력하기
     */
    public void printResult() {
        int[] prizeStat = getPrizeStat();
        int totalreward = getTotalReward(prizeStat);
        double rewardPercent = PurchaseService.getRewardPercent(lottos.size(), totalreward);

        StringBuilder sb = new StringBuilder();
        for (Prize prize : Prize.values()){
            if (prize == Prize.NONE) continue;
            sb.append(prize.getToString());
            sb.append(
                    String.format(" (%,d원) - %d개",
                            prize.getReward(),
                            prizeStat[prize.ordinal()]
                    ));
            sb.append("\n");
        }
        sb.append(String.format("총 수익률은 %.1f%%입니다.", rewardPercent));

        UserOutput.printResult(sb.toString());
    }

    public int[] getPrizeStat() {
        int[] prizeStat = new int[Prize.values().length];
        for (Lotto lotto : lottos) {
            Prize prize = lotto.comparePrize(winningLotto, bonus);
            if (prize != Prize.NONE)
                prizeStat[prize.ordinal()]++;
        }
        return prizeStat;
    }

    public int getTotalReward(int[] prizeStat) {
        int totalReward = 0;
        for (Prize prize : Prize.values()){
            if (prizeStat[prize.ordinal()] != 0)
                totalReward += prize.getReward() * prizeStat[prize.ordinal()];
        }
        return totalReward;
    }

}
