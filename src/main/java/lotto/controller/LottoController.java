package lotto.controller;

import lotto.domain.LottoMoney;
import lotto.domain.Lottos;

import static lotto.view.Input.insertLottoMoney;
import static lotto.view.Input.insertManualLottoCount;

public class LottoController {
    public void start() {
        try {
            LottoMoney money = new LottoMoney(insertLottoMoney());
            // 수동 로또 생성 - 수동 구매 수량 입력 - 수동 구매 번호 입력 -> 생성
            // 자동 로또 생성
            // 모두 담기
            Lottos lottos = createLottos(money);
            // 지난 주 당첨 번호, 보너스 볼 입력
            // 당첨 통계 출력
        } catch (IllegalArgumentException error) {
        }
    }

    private Lottos createLottos(LottoMoney money) {
        int manualLottoCount = insertManualLottoCount();
    }
}
