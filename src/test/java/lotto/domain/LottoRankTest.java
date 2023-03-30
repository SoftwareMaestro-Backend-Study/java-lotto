package lotto.domain;

import java.util.List;
import java.util.stream.Stream;
import lotto.domain.lottorank.LottoRank;
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
        final Lotto winLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        final int bonusBall = 7;
        final WinNumbers winNumbers = new WinNumbers(winLotto, bonusBall);

        final Lotto lotto = new Lotto(numbers);

        // when
        final LottoRank actual = LottoRank.calculateRank(winNumbers, lotto);

        // then
        Assertions.assertThat(actual).isEqualTo(expect);
    }
}
