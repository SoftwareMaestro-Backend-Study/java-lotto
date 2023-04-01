package lotto.domain.result;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfitRateTest {

    @Test
    void 수익률_소숫점_둘째_자리에서_반올림() {
        // given
        int prize = 55000;
        int purchasingMoney = 7000;
        // when
        ProfitRate profitRate = ProfitRate.from(prize, purchasingMoney);
        // then
        assertThat(String.format("%.1f", profitRate.getValue())).isEqualTo("785.7");
    }
}
