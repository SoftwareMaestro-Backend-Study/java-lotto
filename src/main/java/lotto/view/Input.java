package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class Input {
    private static final String MONEY_REQUEST = "구입금액을 입력해 주세요.";
    private static final String MANUAL_LOTTO_COUNT_REQUEST = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_IS_NOT_NUMBER = "문자열이 아닌 숫자(정수)를 입력해주세요.";
    private static final String DUPLICATE_LOTTO_NUMBERS = "로또 번호 중 중복된 번호가 있습니다.";

    public static int insertLottoMoney() {
        System.out.println(MONEY_REQUEST);
        String lottoMoney = Console.readLine();

        return checkNumber(lottoMoney);
    }

    public static int insertManualLottoCount() {
        System.out.println(MANUAL_LOTTO_COUNT_REQUEST);
        String manualLottoCount = Console.readLine();

        return checkNumber(manualLottoCount);
    }

    public static List<Integer> insertLottoNumbers() {
        String lottoNumbersInput = Console.readLine();
        List<String> lottoNumbers = Arrays.asList(lottoNumbersInput.split("\\s*,\\s*"));
        validateLottoNumbers(lottoNumbers);

        return lottoNumbers.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static void validateLottoNumbers(List<String> lottoNumbers) {
        lottoNumbers.forEach(Input::checkNumber);
        validateDuplicateLottoNumbers(lottoNumbers);
    }

    private static void validateDuplicateLottoNumbers(List<String> lottoNumbers) {
        Set<String> uniqueLottoNumbers = new HashSet<>(lottoNumbers);
        if (uniqueLottoNumbers.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_LOTTO_NUMBERS);
        }
    }

    private static int checkNumber(String input) {
        if (!input.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException(INPUT_IS_NOT_NUMBER);
        }
        return parseInt(input);
    }
}
