package lotto.util;

import camp.nextstep.edu.missionutils.Console;

public class UserInput {

    private UserInput() {
    }

    public static String getCostInput() {
        System.out.println("구입금액을 입력해 주세요.");
        return Console.readLine().trim();
    }

    public static String getLottoInput(){
        System.out.println("당첨 번호를 입력해 주세요.");
        return Console.readLine().trim();
    }

    public static String getBonusInput(){
        System.out.println("보너스 번호를 입력해 주세요.");
        return Console.readLine().trim();
    }

}
