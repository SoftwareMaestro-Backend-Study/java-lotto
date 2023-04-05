package lotto.domain.lottocreator;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.domain.LottoNumber;
import lotto.domain.picker.Picker;

public class AutoLottoCreator implements LottoCreator {

    private final Picker picker;

    public AutoLottoCreator(Picker picker) {
        this.picker = picker;
    }

    @Override
    public Set<LottoNumber> create(int lottoSize) {
        return Stream.generate(() -> LottoNumber.pick(this.picker))
                .distinct()
                .limit(lottoSize)
                .collect(Collectors.toSet());
    }
}
