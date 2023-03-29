package lotto.domain.generator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLottoGenerator implements LottoGenerator {

    private static final String DELIMITER = ",";

    private final String input;

    private WinningLottoGenerator(String input) {
        this.input = input;
    }

    public static WinningLottoGenerator from(String input) {
        return new WinningLottoGenerator(input);
    }

    @Override
    public List<Integer> issue(int minValue, int maxValue, int quantity) {
        String[] numbers = input.split(DELIMITER);
        validateDelimiter(numbers, quantity);
        validateSize(numbers, quantity);
        validateDuplication(numbers, quantity);
        validateRange(numbers, minValue, maxValue);
        return Arrays.stream(numbers)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private void validateDelimiter(String[] numbers, int quantity) {
        if (numbers.length != quantity) {
            throw new IllegalArgumentException(String.format("[ERROR] %s 외 구분자가 입력되었습니다.", DELIMITER));
        }
    }

    private void validateDuplication(String[] numbers, int quantity) {
        int numberWithoutDuplication = (int) Arrays.stream(numbers)
                .distinct()
                .count();
        if (numberWithoutDuplication != quantity) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복될 수 없습니다.");
        }
    }

    private void validateSize(String[] numbers, int quantity) {
        if (numbers.length != quantity) {
            throw new IllegalArgumentException(String.format("[ERROR] 당첨 번호의 개수가 %d개가 아닙니다.", quantity));
        }
    }

    private void validateRange(String[] numbers, int minValue, int maxValue) {
        Arrays.stream(numbers)
                .mapToInt(Integer::parseInt)
                .filter(number -> number < minValue || number > maxValue)
                .findAny()
                .ifPresent(number -> {
                    throw new IllegalArgumentException(String.format("[ERROR] 로또 번호는 %d부터 %d 사이의 숫자여야 합니다.", minValue, maxValue));
                });
    }
}
