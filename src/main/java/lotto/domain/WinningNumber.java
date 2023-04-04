package lotto.domain;

import java.util.List;

public class WinningNumber {
    private final Integer number;
    private final NumberType type;

    public WinningNumber(Integer number, NumberType type) {
        this.number = number;
        this.type = type;
    }

}
