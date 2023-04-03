package lotto.domain;

public class LottoMoney {
    private final String MONEY_MUST_OVER_1000 = "구매 금액은 1000원 이상을 입력해주세요.";
    private final String MONEY_UNIT_IS_1000 = "구매 금액은 1000원 단위로 입력해주세요.";
    private final int LOTTO_PRICE_UNIT = 1000;
    private final int price;

    public LottoMoney(int price) {
        validatePrice(price);
        this.price = price;
    }

    private void validatePrice(int price) {
        if (price < LOTTO_PRICE_UNIT) {
            throw new IllegalArgumentException(MONEY_MUST_OVER_1000);
        }
        if (price % LOTTO_PRICE_UNIT != 0) {
            throw new IllegalArgumentException(MONEY_UNIT_IS_1000);
        }
    }
}
