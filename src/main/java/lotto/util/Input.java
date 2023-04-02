package lotto.util;

import camp.nextstep.edu.missionutils.Console;

public class Input {

    private static final String BLANK = " ";
    private static final String DELETE = "";

    private Input() {
    }

    public static String inputPurchasingMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return removeBlank(Console.readLine());
    }

    public static String inputWinningLotto() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
        return removeBlank(Console.readLine());
    }

    public static String inputBonusNumber() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
        return removeBlank(Console.readLine());
    }

    private static String removeBlank(String input) {
        return input.replaceAll(BLANK, DELETE);
    }
}
