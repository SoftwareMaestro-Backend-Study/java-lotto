package lotto.domain;

import lotto.domain.lottocreator.AutoLottoCreator;
import lotto.domain.lottocreator.LottoCreator;
import lotto.domain.picker.RandomPicker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class TicketTest {

    @Test
    void 로또_티켓을_생성할_수_있다() {
        // given
        final LottoCreator autoLottoCreator = new AutoLottoCreator(new RandomPicker());

        // when & then
        Assertions.assertThatCode(() -> Ticket.of(autoLottoCreator, 5))
                .doesNotThrowAnyException();
    }
}
