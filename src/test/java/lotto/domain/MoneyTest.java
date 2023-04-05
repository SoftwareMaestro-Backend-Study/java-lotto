package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 999, 1001})
    void 금액이_단위에_맞지_않으면_예외를_던진다(int value) {
        Assertions.assertThatThrownBy(() -> new Money(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 금액으로_주어진_로또_갯수를_구매할_수_있는지_판단한다() {
        // given
        final Money money = new Money(10000);

        // when
        final boolean result = money.canBuy(10);

        // then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    void 금액으로_주어진_로또_갯수를_구매할_수_없는지_판단한다() {
        // given
        final Money money = new Money(10000);

        // when
        final boolean result = money.canBuy(11);

        // then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    void 금액으로_주어진_로또_갯수를_구매하고_남은_구매_가능_갯수를_반환한다() {
        // given
        final Money money = new Money(10000);

        // when
        final int result = money.remainCount(10);

        // then
        Assertions.assertThat(result).isEqualTo(0);
    }
}
