package lotto.domain;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.picker.Picker;

public class LottoNumber {
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 45;
    private static final List<LottoNumber> VALUES;

    static {
        VALUES = IntStream.rangeClosed(MIN_VALUE, MAX_VALUE)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private final int value;

    private LottoNumber(int value) {
        this.value = value;
    }

    public static LottoNumber pick(Picker picker) {
        return LottoNumber.from(picker.pick(MIN_VALUE, MAX_VALUE));
    }

    public static LottoNumber from(int value) {
        validateRange(value);
        return VALUES.get(value - 1);
    }

    private static void validateRange(int value) {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new IllegalArgumentException(String.format("[ERROR] 로또 숫자는 %d일 수 없습니다.", value));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
