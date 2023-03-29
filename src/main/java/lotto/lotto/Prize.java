package lotto.lotto;

public enum Prize {
    FIFTH(3, false, 5000, "3개 일치"),
    FOURTH(4, false, 50000, "4개 일치"),
    THIRD(5, false, 1500000, "5개 일치"),
    SECOND(5, true, 30000000, "5개 일치, 보너스 볼 일치"),
    FIRST(6, false, 2000000000, "6개 일치");


    private final int matchNum;
    private final boolean bonus;
    private final int reward;
    private final String toString;

    Prize(int matchNum, boolean bonus, int reward, String toString) {
        this.matchNum = matchNum;
        this.bonus = bonus;
        this.reward = reward;
        this.toString = toString;
    }
}
