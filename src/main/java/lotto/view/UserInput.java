package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

public class UserInput {

    private UserInput() {
    }

    public static String getCostInput() {
        System.out.println("구입금액을 입력해 주세요.");
        return Console.readLine().trim();
    }

    public static String getManualNumInput() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        return Console.readLine().trim();
    }

    public static List<String> getManualLottoInput(int manualNum) {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < manualNum; i++) {
            list.add(Console.readLine().trim());
        }
        return list;
    }

    public static String getLottoInput() {
        System.out.println("당첨 번호를 입력해 주세요.");
        return Console.readLine().trim();
    }

    public static String getBonusInput() {
        System.out.println("보너스 번호를 입력해 주세요.");
        return Console.readLine().trim();
    }

}
