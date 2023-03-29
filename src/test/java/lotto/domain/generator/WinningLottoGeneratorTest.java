package lotto.domain.generator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoGeneratorTest {

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,6", "31,22,45,10,8,29"})
    void 입력받은_당첨번호로_로또번호_생성(String input) {
        // given
        LottoGenerator winningLottoGenerator = WinningLottoGenerator.from(input);
        // when & then
        assertThatCode(() -> winningLottoGenerator.issue(1, 45, 6))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "31,22,42,10,8,46", "1-2-3-4-5-6", "1,2,3-4-5-6", "1,1,2,3,4,5", "k,1,2,3,4,5"})
    void 입력받은_당첨번호_검증실패시_예외발생(String input) {
        // given
        LottoGenerator winningLottoGenerator = WinningLottoGenerator.from(input);
        // when & then
        assertThatThrownBy(() -> winningLottoGenerator.issue(1, 45, 6))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
