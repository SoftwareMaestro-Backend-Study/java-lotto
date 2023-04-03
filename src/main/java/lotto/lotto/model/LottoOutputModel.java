package lotto.lotto.model;

import java.util.List;

public class LottoOutputModel {
    private final int lottoCount;
    private final List<List<Integer>> lottoNumbers;

    public LottoOutputModel(int lottoCount, List<List<Integer>> lottoNumbers) {
        this.lottoCount = lottoCount;
        this.lottoNumbers = lottoNumbers;
    }

    public int getLottoCount() {
        return lottoCount;
    }

    public List<List<Integer>> getLottoNumbers() {
        return lottoNumbers;
    }
}
