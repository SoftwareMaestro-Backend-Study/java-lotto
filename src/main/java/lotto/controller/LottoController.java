package lotto.controller;

import lotto.domain.LottoMoney;

import static lotto.view.Input.insertLottoMoney;

public class LottoController {
    public void start() {
        try {
            LottoMoney money = new LottoMoney(insertLottoMoney());
            // 수동 로또 구매 - 수동 구매 수량 입력 - 수동 구매 번호 입력 -> 생성
            // 자동 로또 생성
            // 지난 주 당첨 번호, 보너스 볼 입력
            // 당첨 통계 출력
        } catch (IllegalArgumentException error) {
        }
    }
}
