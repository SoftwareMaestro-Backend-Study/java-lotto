package lotto.domain;

import lotto.domain.enumeration.Ranking;

import java.util.Map;

public class LottoResult {
    private final Map<Ranking, Integer> winningInfo;

    public LottoResult(Map<Ranking, Integer> winningInfo) {
        this.winningInfo = winningInfo;
    }

    public Map<Ranking, Integer> getWinningInfo() {
        return this.winningInfo;
    }
}
