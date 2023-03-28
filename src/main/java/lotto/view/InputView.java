package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {
    private static final String DELIMITER = ",";

    public static int requestInteger() {
        final String input = Console.readLine();
        return parseInt(input);
    }

    public static List<Integer> requestWinNumbers() {
        final String input = Console.readLine();

        return Arrays.stream(input.split(DELIMITER))
                .map(InputView::parseInt)
                .collect(Collectors.toList());
    }

    private static int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("[ERROR] 입력값 %s는 숫자가 아닙니다.", value));
        }
    }
}
