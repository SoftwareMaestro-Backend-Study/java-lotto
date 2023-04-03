package lotto.lotto;

import lotto.lotto.lottoCreator.LottoCreator;
import lotto.lotto.lottoCreator.ManualLottoCreator;
import lotto.lotto.lottoCreator.RandomLottoCreator;
import lotto.lotto.model.LottoOutputModel;
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
        LottoBundle.Builder lottoBundleBuilder = new LottoBundle.Builder();

        // 구입금액 입력받기
        // todo : 구입금액 객체 관리
        int lottoNum = PurchaseService.getLottoNum(getNum(UserInput.getCostInput()));
        int manualNum = getNum(UserInput.getManualNumInput());
        if (lottoNum < manualNum)
            throw new IllegalArgumentException("[ERROR] 구입 가능한 로또 수를 초과하였습니다.");

        // 자동 로또
        List<LottoCreator> creators = Stream.generate(RandomLottoCreator::from)
                .limit(lottoNum - manualNum)
                .collect(Collectors.toList());
        // 수동 로또
        if (manualNum != 0) {
            List<String> strList = UserInput.getManualLottoInput(manualNum);
            for (String input : strList)
                creators.add(ManualLottoCreator.from(getNumList(input)));
        }

        lottoBundleBuilder.createLottos(creators);
        LottoBundle bundle = lottoBundleBuilder.build();

        // 로또 출력하기
        UserOutput.printLottos(
                new LottoOutputModel(
                        lottoNum - manualNum, manualNum, bundle.getLottosNumList()
                ));

        // 당첨번호 입력받기
        lottoBundleBuilder.winningLotto(
                ManualLottoCreator.from(getNumList(UserInput.getLottoInput())));

        // 보너스번호 입력받기
        lottoBundleBuilder.bonus(getNum(UserInput.getBonusInput()));
        bundle = lottoBundleBuilder.build();

        // 당첨 통계 출력하기
        UserOutput.printResult(bundle.getResultStatus());
    }


    public int getNum(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ne) {
            throw new IllegalArgumentException("[ERROR] 정수를 입력해주세요.");
        }
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
