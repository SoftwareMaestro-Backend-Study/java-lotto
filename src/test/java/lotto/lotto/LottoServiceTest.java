package lotto.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class LottoServiceTest {

    @ParameterizedTest
    @DisplayName("getNumList 동작 테스트")
    @CsvSource(value = {"1,1","2,2","3,3","4,4"}, delimiter = ',')
    void getNum_동작(String input, int expected) {
        // given
        LottoService lottoService = new LottoService();
        // when
        int test = lottoService.getNum(input);
        // then
        assertThat(test).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("getNumList 예와 테스트")
    @ValueSource(strings = {"a", "--1", ",,3"})
    void getNum_예와(String input) {
        // given
        LottoService lottoService = new LottoService();
        // when & then
        assertThatThrownBy(()->lottoService.getNum(input))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @ParameterizedTest
    @DisplayName("getNumList 동작 테스트")
    @ValueSource(strings = {"1", "1,2", "1,200,3000,2,1", "99999,99999,99999,99999,99999,99999"})
    void getNumList_동작(String input) {
        // given
        LottoService lottoService = new LottoService();
        // when & then
        assertThatCode(()->lottoService.getNumList(input))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("getNumList 예외 테스트")
    @ValueSource(strings = {"1,,", "d,s,1", "--1,2,3"})
    void getNumList_예외(String input) {
        // given
        LottoService lottoService = new LottoService();
        // when & then
        assertThatThrownBy(()->lottoService.getNumList(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

}