package lotto.domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import lotto.domain.lottocreator.AutoLottoCreator;
import lotto.domain.lottocreator.LottoCreator;
import lotto.domain.lottocreator.ManualLottoCreator;
import lotto.domain.picker.RandomPicker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    public static Stream<Arguments> invalidLotto() {
        return Stream.of(
                Arguments.of(Set.of(LottoNumber.from(1), LottoNumber.from(2), LottoNumber.from(3),
                        LottoNumber.from(4), LottoNumber.from(5))),
                Arguments.of(Set.of(LottoNumber.from(1), LottoNumber.from(2), LottoNumber.from(3),
                        LottoNumber.from(4), LottoNumber.from(5), LottoNumber.from(6), LottoNumber.from(7))
                ));
    }

    public static Stream<Arguments> LottoCreator() {
        return Stream.of(
                Arguments.of(new AutoLottoCreator(new RandomPicker())),
                Arguments.of(new ManualLottoCreator(List.of(1, 2, 3, 4, 5, 6)))
        );
    }

    @ParameterizedTest
    @MethodSource("invalidLotto")
    void 로또_숫자가_6개가_아니면_예외를_던진다(Set<LottoNumber> numbers) {
        Assertions.assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("LottoCreator")
    void 로또_생성기로_로또를_생성할_수_있다(LottoCreator creator) {
        Assertions.assertThatCode(() -> Lotto.create(creator))
                .doesNotThrowAnyException();
    }
}
