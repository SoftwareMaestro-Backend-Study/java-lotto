package lotto.view;

import lotto.lotto.model.LottoOutputModel;
import lotto.lotto.model.StatusOutputModel;

import java.util.List;

public class UserOutput {

    private UserOutput() {
    }

    public static void printLottos(LottoOutputModel model) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n수동으로 ");
        sb.append(model.getManualCount())
                .append("개, 자동으로 ");
        sb.append(model.getAutoCount())
                .append("개를 구매했습니다.\n");
        for (List<Integer> list : model.getLottoNumbers())
            sb.append(list).append("\n");
        System.out.println(sb);
    }

    public static void printResult(StatusOutputModel model) {
        System.out.println("\n당첨 통계\n" +
                "---");
        System.out.println(model.getResultStr());
    }
}
