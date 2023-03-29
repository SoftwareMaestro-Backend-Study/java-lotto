package lotto.util;

import lotto.domain.Lotto;
import lotto.domain.Lottos;

public class Output {

    private Output() {
    }

    public static void printIssuedLotto(Lottos issuedLotto) {
        System.out.printf("\n%d개를 구매했습니다.%n", issuedLotto.getQuantity());
        issuedLotto.getLottos()
                .stream()
                .map(Lotto::getNumbers)
                .map(String::valueOf)
                .forEach(System.out::println);
    }
}
