package lotto.domain.picker;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomPicker implements Picker {

    @Override
    public int pick(int minValue, int maxValue) {
        return Randoms.pickNumberInRange(maxValue, maxValue);
    }
}
