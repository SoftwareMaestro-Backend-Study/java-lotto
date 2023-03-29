package lotto.purchase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PurchaseServiceTest {

    @ParameterizedTest
    @DisplayName("getLottoNum 동작 테스트")
    @CsvSource(value = {"1000,1", "2000,2", "3000,3", "4000,4"}, delimiter = ',')
    void getLottoNum_동작(int cost, int expect) {
        // when
        int test = PurchaseService.getLottoNum(cost);
        // then
        assertThat(test).isEqualTo(expect);
    }

    @ParameterizedTest
    @DisplayName("getLottoNum 예외 테스트")
    @ValueSource(ints = {0, 1005, 2050, 3500})
    void getLottoNum_예외(int cost) {
        // when & then
        assertThatThrownBy(() ->
                PurchaseService.getLottoNum(cost)
        ).isInstanceOf(IllegalArgumentException.class);
    }

}