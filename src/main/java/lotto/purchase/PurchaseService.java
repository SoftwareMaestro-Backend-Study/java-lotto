package lotto.purchase;

public class PurchaseService {

    private final static int PRICE_PER_LOTTO = 1000;

    private PurchaseService() {
    }

    /**
     * 텍스트 입력을 lotto 수로 변환
     */
    public static int getLottoNum(int cost) {
        validatePositiveNum(cost);
        validateDivisible(cost);
        return cost / PRICE_PER_LOTTO;
    }

    /**
     * 총 로또 수익률을 퍼센트 단위로 반환
     */
    public static double getRewardPercent(int num, int reward){
        return (double) reward / (num * PRICE_PER_LOTTO) * 100;
    }

    private static void validatePositiveNum(int num) {
        if (num <=0 )
            throw new IllegalArgumentException("[ERROR] 1 이상의 양수를 입력해주세요");
    }

    private static void validateDivisible(int num) {
        if (num % PRICE_PER_LOTTO != 0)
            throw new IllegalArgumentException("[ERROR] 가격의 배수에 해당하는 가격을 입력해주세요");
    }
}
