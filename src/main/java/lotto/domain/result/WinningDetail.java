package lotto.domain.result;

import java.util.*;
import java.util.stream.Collectors;

public class WinningDetail {

    private final Map<LottoResult, Integer> lottoResults;

    private WinningDetail(Map<LottoResult, Integer> lottoResultIntegerMap) {
        this.lottoResults = new EnumMap<>(lottoResultIntegerMap);
    }

    public static WinningDetail from(List<LottoResult> lottoResults) {
        Map<LottoResult, Integer> lottoResultMap = getLottoResultMap();
        lottoResults.forEach(lottoResult -> lottoResultMap.computeIfPresent(lottoResult, (key, value) -> value + 1));
        return new WinningDetail(lottoResultMap);
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

    private static Map<LottoResult, Integer> getLottoResultMap() {
        Map<LottoResult, Integer> lottoResultCounts = new EnumMap<>(LottoResult.class);
        Arrays.stream(LottoResult.values())
                .forEach(lottoResult -> lottoResultCounts.put(lottoResult, 0));
        return lottoResultCounts;
    }
}
