package lotto.domain;

import lotto.util.Convertor;

public class PurchasingMoney {

    private static final int UNIT = 1000;

    private final int money;
    private final int quantity;

    private PurchasingMoney(int money) {
        validateUnit(money);
        this.money = money;
        this.quantity = money / UNIT;
    }

    public static PurchasingMoney from(String input) {
        return new PurchasingMoney(Convertor.toInteger(input));
    }

    public int getMoney() {
        return money;
    }

    public int getQuantity() {
        return quantity;
    }

    private void validateUnit(int money) {
        if (money % UNIT != 0) {
            throw new IllegalArgumentException(String.format("[ERROR] 구입금액은 %d원 단위여야 합니다.", UNIT));
        }
    }
}
