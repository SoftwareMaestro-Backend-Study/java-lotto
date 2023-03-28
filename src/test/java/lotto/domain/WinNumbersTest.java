package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WinNumbersTest {

    @Test
    void 보너스볼이_로또_넘버와_중복되면_예외를_던진다() {
        // given
        final Integer bonusBall = 6;
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, bonusBall));

        // when & then
        Assertions.assertThatThrownBy(() -> new WinNumbers(lotto, bonusBall))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] %d는 보너스 볼이 될 수 없습니다.", bonusBall);
    }
}
