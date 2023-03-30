package lotto.domain.lottorank;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoRanks {

    private final Map<LottoRank, Integer> ranks;

    private LottoRanks(Map<LottoRank, Integer> ranks) {
        this.ranks = ranks;
    }

    public static LottoRanks from(List<LottoRank> ranks) {
        EnumMap<LottoRank, Integer> lottoRankMap = new EnumMap<>(LottoRank.class);
        Arrays.stream(LottoRank.values()).forEach(lottoRank -> lottoRankMap.put(lottoRank, 0));

        ranks.forEach(rank -> lottoRankMap.computeIfPresent(rank, (key, value) -> value + 1));
        return new LottoRanks(lottoRankMap);
    }

    public int getProfit() {
        return this.ranks.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }

    @Override
    public String toString() {
        return ranks.entrySet()
                .stream()
                .map(entry -> String.format("%s - %d개", entry.getKey().toString(), entry.getValue()))
                .collect(Collectors.joining("\n"));
    }
}
