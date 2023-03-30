package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 999, 1001, 10001})
    void 금액이_1000원_미만이거나_1000으로_나눠지지_않는_경우_예외를_던진다(int value) {
        Assertions.assertThatThrownBy(() -> new Money(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 12000})
    void 금액이_1000원으로_나눠지는_경우_성공(int value) {
        Assertions.assertThatCode(() -> new Money(value))
                .doesNotThrowAnyException();
    }
}
