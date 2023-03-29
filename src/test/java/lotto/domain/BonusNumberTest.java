package lotto.domain;

import lotto.domain.generator.LottoGenerator;
import lotto.domain.generator.WinningLottoGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BonusNumberTest {

    @ParameterizedTest
    @ValueSource(strings = {"7", "12", "45"})
    void 당첨번호와_미중복시_보너스번호_생성(String input) {
        // given
        LottoGenerator winningLottoGenerator = WinningLottoGenerator.from("1,2,3,4,5,6");
        Lotto lotto = Lotto.from(winningLottoGenerator);
        // when & then
        assertThatCode(() -> BonusNumber.from(lotto, input))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"6", "k2", "46"})
    void 보너스번호_검증실패시_예외발생(String input) {
        // given
        LottoGenerator winningLottoGenerator = WinningLottoGenerator.from("1,2,3,4,5,6");
        Lotto lotto = Lotto.from(winningLottoGenerator);
        // when & then
        assertThatThrownBy(() -> BonusNumber.from(lotto, input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
