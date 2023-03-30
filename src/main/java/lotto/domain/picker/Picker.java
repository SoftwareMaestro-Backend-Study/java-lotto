package lotto.domain.picker;

public interface Picker<T> {
    T pick(T minValue, T maxValue);
}
