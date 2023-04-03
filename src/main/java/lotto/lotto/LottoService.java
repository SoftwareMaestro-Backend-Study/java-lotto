package lotto.lotto;

import lotto.lotto.lottoCreator.ManualLottoCreator;
import lotto.lotto.lottoCreator.RandomLottoCreator;
import lotto.purchase.PurchaseService;
import lotto.view.UserInput;
import lotto.view.UserOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoService {

    public void run() {
        LottoBundle bundle = new LottoBundle();

        // 구입금액 입력받기
        int cost = getNum(UserInput.getCostInput());
        int lottoNum = PurchaseService.getLottoNum(cost);
        bundle.createLottos(
                Stream.generate(RandomLottoCreator::from)
                        .limit(lottoNum)
                        .collect(Collectors.toList())
        );

        // 로또 출력하기
        UserOutput.printLottos(bundle.getLottosInfo());

        // 당첨번호 입력받기
        List<Integer> winningList = getNumList(UserInput.getLottoInput());
        bundle.setWinningLotto(ManualLottoCreator.from(winningList));

        // 보너스번호 입력받기
        int bonus = getNum(UserInput.getBonusInput());
        bundle.setBonus(bonus);

        // 당첨 통계 출력하기
        UserOutput.printResult(bundle.getResultStatus());
    }


    public int getNum(String input) {
        int num;
        try {
            num = Integer.parseInt(input);
        } catch (NumberFormatException ne) {
            throw new IllegalArgumentException("[ERROR] 정수를 입력해주세요.");
        }
        return num;
    }

    public List<Integer> getNumList(String input) {
        List<String> strList = new ArrayList<>(
                Arrays.asList(
                        input.split(",")
                )
        );

        // "," 개수를 이용해서 배열의 길이가 올바르게 입력되었는지 확인한다.
        int commas = input.length() - input.replace(String.valueOf(","), "").length();
        if (commas + 1 != strList.size())
            throw new IllegalArgumentException("[ERROR] 정수와 ,로 이루어진 배열을 입력해주세요.");

        List<Integer> numList = strList.stream()
                .map(s -> {
                    try {
                        return Integer.parseInt(s);
                    } catch (NumberFormatException ne) {
                        throw new IllegalArgumentException("[ERROR] 정수와 ,로 이루어진 배열을 입력해주세요.");
                    }
                }).collect(Collectors.toList());

        return numList;
    }

}
