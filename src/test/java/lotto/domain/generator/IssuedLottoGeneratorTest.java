package lotto.domain.generator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class IssuedLottoGeneratorTest {

    @DisplayName("로또 발행")
    @Test
    void 로또_발행() {
        // given
        LottoGenerator issuedLottoGenerator = IssuedLottoGenerator.create();
        // when & then
        assertThatCode(() -> issuedLottoGenerator.issue(1, 45, 6))
                .doesNotThrowAnyException();
    }
}
