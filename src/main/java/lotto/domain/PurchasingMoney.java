package lotto.domain;

public class PurchasingMoney {

    private static final int UNIT = 1000;

    private final int money;

    private PurchasingMoney(int money) {
        validatePositive(money);
        validateUnit(money);
        this.money = money;
    }

    public static PurchasingMoney from(int money) {
        return new PurchasingMoney(money);
    }

    public int getMoney() {
        return money;
    }

    public int getQuantity() {
        return money / UNIT;
    }

    private void validatePositive(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("[ERROR] 구입금액은 양수여야 합니다.");
        }
    }

    private void validateUnit(int money) {
        if (money % UNIT != 0) {
            throw new IllegalArgumentException(String.format("[ERROR] 구입금액은 %d원 단위여야 합니다.", UNIT));
        }
    }
}
