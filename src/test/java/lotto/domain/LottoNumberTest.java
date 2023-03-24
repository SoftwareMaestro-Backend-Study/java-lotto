package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    void 로또_숫자가_1부터_45_사이의_수가_아니면_예외를_던진다(int value) {
        Assertions.assertThatThrownBy(() -> LottoNumber.from(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    void 로또_숫자가_1부터_45_사이의_수라면_로또_넘버를_생성할_수_있다(int value) {
        Assertions.assertThatCode(() -> LottoNumber.from(value))
                .doesNotThrowAnyException();
    }
}
