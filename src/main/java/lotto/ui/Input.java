package lotto.ui;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Input {

    private static final String DELIMITER = ", ";


    private Input() {
        throw new UnsupportedOperationException();
    }

    public static int getMoneyValue() {
        System.out.println("구입금액을 입력해 주세요.");
        final String input = Console.readLine();
        return getAnInt(input);
    }

    private static int getAnInt(String input) {
        return Integer.parseInt(input);
    }

    public static int getManualLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        final String input = Console.readLine();
        return getAnInt(input);
    }

    public static List<List<Integer>> getManualLottos(int manualLottoCount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        return IntStream.range(0, manualLottoCount)
                .mapToObj(i -> Console.readLine())
                .map(lotto -> Arrays.stream(lotto.split(DELIMITER)).map(Input::getAnInt).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

}
