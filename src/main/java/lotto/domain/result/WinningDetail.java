package lotto.domain.result;

import java.text.DecimalFormat;
import java.util.List;

public class WinningDetail {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###");

    private final int firstPrize;
    private final int secondPrize;
    private final int thirdPrize;
    private final int fourthPrize;
    private final int fifthPrize;
    private final int totalPrize;

    private WinningDetail(int firstPrize, int secondPrize, int thirdPrize, int fourthPrize, int fifthPrize, int totalPrize) {
        this.firstPrize = firstPrize;
        this.secondPrize = secondPrize;
        this.thirdPrize = thirdPrize;
        this.fourthPrize = fourthPrize;
        this.fifthPrize = fifthPrize;
        this.totalPrize = totalPrize;
    }

    public static WinningDetail from(List<LottoResult> lottoResults) {
        int firstPrize = (int) lottoResults.stream().filter(lottoResult -> LottoResult.FIRST_PRIZE == lottoResult).count();
        int secondPrize = (int) lottoResults.stream().filter(lottoResult -> LottoResult.SECOND_PRIZE == lottoResult).count();
        int thirdPrize = (int) lottoResults.stream().filter(lottoResult -> LottoResult.THIRD_PRIZE == lottoResult).count();
        int fourthPrize = (int) lottoResults.stream().filter(lottoResult -> LottoResult.FOURTH_PRIZE == lottoResult).count();
        int fifthPrize = (int) lottoResults.stream().filter(lottoResult -> LottoResult.FIFTH_PRIZE == lottoResult).count();
        int totalPrize = lottoResults.stream().mapToInt(LottoResult::getPrize).sum();
        return new WinningDetail(firstPrize, secondPrize, thirdPrize, fourthPrize, fifthPrize, totalPrize);
    }

    public String getResult() {
        StringBuilder detail = new StringBuilder();
        detail.append(String.format("%s (%s원) - %d개\n", LottoResult.FIFTH_PRIZE.getMessage(), DECIMAL_FORMAT.format(LottoResult.FIFTH_PRIZE.getPrize()), fifthPrize))
                .append(String.format("%s (%s원) - %d개\n", LottoResult.FOURTH_PRIZE.getMessage(), DECIMAL_FORMAT.format(LottoResult.FOURTH_PRIZE.getPrize()), fourthPrize))
                .append(String.format("%s (%s원) - %d개\n", LottoResult.THIRD_PRIZE.getMessage(), DECIMAL_FORMAT.format(LottoResult.THIRD_PRIZE.getPrize()), thirdPrize))
                .append(String.format("%s (%s원) - %d개\n", LottoResult.SECOND_PRIZE.getMessage(), DECIMAL_FORMAT.format(LottoResult.SECOND_PRIZE.getPrize()), secondPrize))
                .append(String.format("%s (%s원) - %d개\n", LottoResult.FIRST_PRIZE.getMessage(), DECIMAL_FORMAT.format(LottoResult.FIRST_PRIZE.getPrize()), firstPrize));
        return detail.toString();
    }

    public int getTotalPrize() {
        return totalPrize;
    }
}
