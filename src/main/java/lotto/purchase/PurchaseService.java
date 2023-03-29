package lotto.purchase;

public class PurchaseService {

    private final static int PRICE_PER_LOTTO = 1000;

    private PurchaseService() {
    }

    /**
     * 텍스트 입력을 lotto 수로 변환
     */
    public static int getLottoNum(int cost) {
        if (cost <=0 )
            throw new IllegalArgumentException("[ERROR] 1 이상의 양수를 입력해주세요");
        if (cost % PRICE_PER_LOTTO != 0)
            throw new IllegalArgumentException("[ERROR] 가격의 배수에 해당하는 가격을 입력해주세요");

        return cost / PRICE_PER_LOTTO;
    }

    public static double getRewardPercent(int num, int reward){
        return (double) reward / (num * PRICE_PER_LOTTO) * 100;
    }


}
