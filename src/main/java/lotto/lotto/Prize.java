package lotto.lotto;

import java.util.EnumMap;
import java.util.Map;

public enum Prize {
    FIFTH(true, 5, 3, false, 5000, "3개 일치"),
    FOURTH(true, 4, 4, false, 50000, "4개 일치"),
    THIRD(true, 3, 5, false, 1500000, "5개 일치"),
    SECOND(true, 2, 5, true, 30000000, "5개 일치, 보너스 볼 일치"),
    FIRST(true, 1, 6, false, 2000000000, "6개 일치"),
    NONE(false, 99999, 0, false, 0, "");

    private final boolean prized;
    private final int grade;
    private final int matchNum;
    private final boolean bonus;
    private final int reward;
    private final String toString;

    Prize(boolean prized, int grade, int matchNum, boolean bonus, int reward, String toString) {
        this.prized = prized;
        this.grade = grade;
        this.matchNum = matchNum;
        this.bonus = bonus;
        this.reward = reward;
        this.toString = toString;
    }

    public static Map<Prize, Integer> getInitializedMap() {
        Map<Prize, Integer> prizeMap = new EnumMap<Prize, Integer>(Prize.class);
        for( Prize key : Prize.values() ){
            prizeMap.put(key, 0);
        }
        return prizeMap;
    }

    public boolean isPrized() {
        return prized;
    }

    public int getGrade() {
        return grade;
    }

    public int getMatchNum() {
        return matchNum;
    }

    public boolean isBonus() {
        return bonus;
    }

    public int getReward() {
        return reward;
    }

    public String getToString() {
        return toString;
    }

}
