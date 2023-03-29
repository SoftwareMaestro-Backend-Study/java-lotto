package lotto.util;

public class UserOutput {

    private UserOutput() {
    }

    public static void printLottos(int num, String lottoStr) {
        StringBuilder sb = new StringBuilder();
        sb.append(num).append("개를 구매했습니다.\n").append(lottoStr);
        System.out.println(sb);
    }

    public static void printResult(String resultStr){
        System.out.println( "당첨 통계\n" +
                "---\n");
        System.out.println(resultStr);
    }

}
