package lotto.domain.lottorank;

import static lotto.domain.lottorank.LottoRank.FIFTH;
import static lotto.domain.lottorank.LottoRank.FIRST;
import static lotto.domain.lottorank.LottoRank.FOURTH;
import static lotto.domain.lottorank.LottoRank.SECOND;
import static lotto.domain.lottorank.LottoRank.THIRD;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

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
        return String.format("3개 일치 (%,d원) - %d개\n", FIFTH.getPrize(), ranks.get(FIFTH))
                + String.format("4개 일치 (%,d원) - %d개\n", FOURTH.getPrize(), ranks.get(FOURTH))
                + String.format("5개 일치 (%,d원) - %d개\n", THIRD.getPrize(), ranks.get(THIRD))
                + String.format("5개 일치, 보너스 볼 일치 (%,d원) - %d개\n", SECOND.getPrize(), ranks.get(SECOND))
                + String.format("6개 일치 (%,d원) - %d개\n", FIRST.getPrize(), ranks.get(FIRST));
    }
}
