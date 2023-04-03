package lotto.domain;

import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WinningLottoTest {

    @Test
    void 보너스_볼이_이미_로또에_존재하면_예외를_던진다() {
        // given
        final LottoNumber bonusBall = LottoNumber.from(1);
        final Lotto winningLotto = Lotto.create(
                lottoCount -> Set.of(bonusBall, LottoNumber.from(2), LottoNumber.from(3),
                        LottoNumber.from(4), LottoNumber.from(5), LottoNumber.from(6)));

        // when & then
        Assertions.assertThatThrownBy(() -> new WinningLotto(winningLotto, bonusBall))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
