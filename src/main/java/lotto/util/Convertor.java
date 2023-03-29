package lotto.util;

public class Convertor {

    private Convertor() {
    }

    public static int toInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 정수가 아닌 값이 포함되었습니다.");
        }
    }
}
