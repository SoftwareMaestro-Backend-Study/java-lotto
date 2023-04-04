package lotto.domain;

import lotto.domain.enumeration.NumberType;

public class WinningNumber {
    private final Integer number;
    private final NumberType type;

    public WinningNumber(Integer number, NumberType type) {
        this.number = number;
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public boolean isBonus() {
        return type == NumberType.BOUNS;
    }

}
