package lotto.domain;

import lotto.domain.Lotto;
import lotto.domain.lottonumbercreator.LottoNumberCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 숫자의 개수는 7일 수 없습니다.");
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 중복되는 숫자가 존재합니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"0,1,2,3,4,5", "1,2,3,4,5,46"})
    void 로또_번호의_범위를_벗어나는_숫자가_있으면_예외를_던진다(Integer n1, Integer n2, Integer n3, Integer n4, Integer n5, Integer n6) {
        assertThatThrownBy(() -> new Lotto(List.of(n1, n2, n3, n4, n5, n6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 1 ~ 45 범위를 넘어가는 숫자가 존재합니다.");
    }

    @Test
    void 자동_로또를_생성한다() {
        // given
        final List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        LottoNumberCreator creator = (min, max, size) -> numbers;

        // when
        final Lotto lotto = Lotto.from(creator);

        // then
        assertThat(lotto).extracting("numbers")
                .isEqualTo(numbers);
    }
}
