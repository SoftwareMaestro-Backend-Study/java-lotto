package lotto.domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lotto.domain.lottocreator.AutoLottoCreator;
import lotto.domain.lottocreator.LottoCreator;
import lotto.domain.lottocreator.ManualLottoCreator;
import lotto.domain.picker.RandomPicker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    public static Stream<Arguments> invalidLotto() {
        return Stream.of(Arguments.of(List.of(1, 2, 3, 4, 5)), Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7)));
    }

    public static Stream<Arguments> LottoCreator() {
        return Stream.of(
                Arguments.of(new AutoLottoCreator(new RandomPicker())),
                Arguments.of(new ManualLottoCreator(List.of(1, 2, 3, 4, 5, 6)))
        );
    }

    @ParameterizedTest
    @MethodSource("LottoCreator")
    void 로또_생성기로_로또를_생성할_수_있다(LottoCreator creator) {
        Assertions.assertThatCode(() -> Lotto.create(creator))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource("invalidLotto")
    void 로또_숫자가_6개가_아니면_예외를_던진다(List<Integer> numbers) {
        Assertions.assertThatThrownBy(() -> Lotto.create(new ManualLottoCreator(numbers)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_숫자가_존재하는지_확인한다() {
        // given
        final LottoNumber bonusBall = LottoNumber.from(1);
        final Lotto lotto = Lotto.create(count -> IntStream.rangeClosed(1, 6)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toSet()));

        // when
        final boolean result = lotto.contains(bonusBall);

        // then
        Assertions.assertThat(result).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "6,1,2,3,4,5,6",
            "5,1,2,3,4,5,7",
            "4,1,2,3,4,7,8",
            "3,1,2,3,7,8,9",
            "2,1,2,7,8,9,10",
            "1,1,7,8,9,10,11",
            "0,7,8,9,10,11,12"
    })
    void 같은_숫자가_몇_개인지_반환한다(int expected, int n1, int n2, int n3, int n4, int n5, int n6) {
        // given
        final Lotto winningLotto = Lotto.create(count -> IntStream.rangeClosed(1, 6)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toSet()));

        final Lotto lotto = Lotto.create(
                count -> Set.of(LottoNumber.from(n1), LottoNumber.from(n2), LottoNumber.from(n3),
                        LottoNumber.from(n4), LottoNumber.from(n5), LottoNumber.from(n6)));

        // when
        final int actual = winningLotto.calculateSameLottoNumber(lotto);

        // then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
