package lotto.lotto.model;

import java.util.List;

public class LottoOutputModel {
    private final int autoCount;
    private final int manualCount;
    private final List<List<Integer>> lottoNumbers;

    public LottoOutputModel(int autoCount, int manualCount, List<List<Integer>> lottoNumbers) {
        this.autoCount = autoCount;
        this.manualCount = manualCount;
        this.lottoNumbers = lottoNumbers;
    }

    public int getAutoCount() {
        return autoCount;
    }

    public int getManualCount() {
        return manualCount;
    }

    public List<List<Integer>> getLottoNumbers() {
        return lottoNumbers;
    }
}
