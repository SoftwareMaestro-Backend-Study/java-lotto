package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    void 로또_숫자_생성_시_로또_숫자의_범위에_속하지_않으면_예외를_던진다(int value) {
        Assertions.assertThatThrownBy(() -> LottoNumber.from(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("[ERROR] 로또 숫자는 %d일 수 없습니다.", value));
    }
}
