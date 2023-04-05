package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoRankTest {

    public static Stream<Arguments> findRank() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), LottoRank.FIRST),
                Arguments.of(List.of(1, 2, 3, 4, 5, 7), LottoRank.SECOND),
                Arguments.of(List.of(1, 2, 3, 4, 5, 8), LottoRank.THIRD),
                Arguments.of(List.of(1, 2, 3, 4, 7, 8), LottoRank.FOURTH),
                Arguments.of(List.of(1, 2, 3, 7, 8, 9), LottoRank.FIFTH),
                Arguments.of(List.of(1, 2, 7, 8, 9, 10), LottoRank.OTHER)
        );
    }

    @ParameterizedTest
    @MethodSource("findRank")
    void 로또_당첨_등수를_확인한다(List<Integer> numbers, LottoRank expect) {
        // given
        final Lotto winningLotto = Lotto.create(count -> IntStream.rangeClosed(1, 6)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toSet()));

        final LottoNumber bonusBall = LottoNumber.from(7);
        final WinningLotto winNumbers = new WinningLotto(winningLotto, bonusBall);

        final Lotto lotto = Lotto.create(count -> numbers.stream().map(LottoNumber::from).collect(Collectors.toSet()));

        // when
        final LottoRank actual = LottoRank.calculateRank(winNumbers, lotto);

        // then
        Assertions.assertThat(actual).isEqualTo(expect);
    }
}
