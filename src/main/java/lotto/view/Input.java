package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Input {
    private static final String PURCHASEMONEY_REQUEST = "구입금액을 입력해 주세요.";
    private static final String INPUT_IS_NOT_NUMBER = "문자열이 아닌 숫자(정수)를 입력해주세요.";

    public static int insertLottoMoney() {
        System.out.println(PURCHASEMONEY_REQUEST);

        String purchaseMoney = Console.readLine();
        checkNumber(purchaseMoney);
        return parseInt(purchaseMoney);
    }

    private static void checkNumber(String input) {
        if (!input.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException(INPUT_IS_NOT_NUMBER);
        }
    }

    private static List<Integer> parsePrizeNumbers(List<String> splitedInput) {
        return splitedInput.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
