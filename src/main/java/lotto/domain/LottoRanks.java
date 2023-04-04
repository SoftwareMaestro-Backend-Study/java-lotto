package lotto.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LottoRanks {
    private final Map<LottoRank, Integer> ranks;

    private LottoRanks(Map<LottoRank, Integer> ranks) {
        this.ranks = ranks;
    }

    public static LottoRanks from(List<LottoRank> ranks) {
        return new LottoRanks(
                ranks.stream().collect(Collectors.toMap(Function.identity(), rank -> 1, Integer::sum))
        );
    }

    public int getProfit() {
        return this.ranks.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }

    @Override
    public String toString() {
        Arrays.stream(LottoRank.values())
                .forEach(rank -> this.ranks.computeIfAbsent(rank, value -> 0));

        return ranks.entrySet()
                .stream()
                .filter(entry -> entry.getKey().isNotOtherRank())
                .sorted(Entry.comparingByKey())
                .map(entry -> String.format("%s - %d개", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining("\n"));
    }
}
