package lotto.lotto;

import lotto.util.LottoUtil;
import lotto.util.UserOutput;

import java.util.ArrayList;
import java.util.List;

public class LottoBundle {
    private List<Lotto> lottos = new ArrayList<>();
    private Lotto winningLotto ;
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

    // 당첨번호 입력받기
    public void setWinningLotto(List<Integer> winningNumList){
        Lotto lotto = new Lotto(winningNumList);
        this.winningLotto = lotto;
    }

    // 보너스번호 입력받기
    public void setBonus(int bonus){
        this.bonus = bonus;
    }

    // 로또 출력하기
    public void printLottos() {
        StringBuilder sb = new StringBuilder();

        for (Lotto lotto : lottos){
            sb.append(lotto).append("\n");
        }

        UserOutput.printLottos(0, sb.toString());
    }

    // 당첨 통계 출력하기
    public void printResult() {
        String str = "";

        int[] prizeStat = getPrizeStat();
        int totalreward = getTotalReward(prizeStat);

        UserOutput.printResult(str);
    }

    public int[] getPrizeStat() {
        return new int[Prize.values().length];
    }
    public int getTotalReward(int[] prizeStat){
        return 0;
    }
    
}
