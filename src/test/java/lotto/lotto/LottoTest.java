package lotto.lotto;

import lotto.lotto.Lotto;
import lotto.util.LottoUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }


    List<List<Integer>> testList = new ArrayList<>(List.of(
            List.of(1, 2, 3, 10, 11, 12),
            List.of(1, 2, 3, 4, 11, 12),
            List.of(1, 2, 3, 4, 5, 12),
            List.of(1, 2, 3, 4, 5, 7),
            List.of(1, 2, 3, 4, 5, 6)
    ));

    @ParameterizedTest
    @DisplayName("comparePrize 동작 테스트")
    @CsvSource(value = {"0:FIFTH", "1:FOURTH", "2:THIRD", "3:SECOND", "4:FIRST"}, delimiter = ':')
    void comparePrize_동작(int testNum, String prizeName) {
        // given
        Lotto myLotto = new Lotto(testList.get(testNum));
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonus = 7;

        // when
        Prize p = myLotto.comparePrize(winningLotto, bonus);

        // then
        assertThat(p).isEqualTo(Prize.valueOf(prizeName));
    }

    @ParameterizedTest
    @DisplayName("toString 동작 테스트")
    @CsvSource(value = {
            "0:[1, 2, 3, 10, 11, 12]",
            "1:[1, 2, 3, 4, 11, 12]",
            "2:[1, 2, 3, 4, 5, 12]"
    }, delimiter = ':')
    void toString_동작(int testNum, String expected) {
        // given
        Lotto myLotto = new Lotto(testList.get(testNum));

        // when
        String test = myLotto.toString();

        // then
        assertThat(test).isEqualTo(expected);
    }

}
