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

    public int getTotalPrize() {
        return lottoResults.entrySet()
                .stream()
                .mapToInt(lottoResult -> lottoResult.getKey().getPrize() * lottoResult.getValue())
                .sum();
    }

    private static Map<LottoResult, Integer> getLottoResultMap() {
        Map<LottoResult, Integer> lottoResultCounts = new EnumMap<>(LottoResult.class);
        Arrays.stream(LottoResult.values())
                .forEach(lottoResult -> lottoResultCounts.put(lottoResult, 0));
        return lottoResultCounts;
    }
}
