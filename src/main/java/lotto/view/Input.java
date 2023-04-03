package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class Input {
    private static final String MONEY_REQUEST = "구입금액을 입력해 주세요.";
    private static final String MANUAL_LOTTO_COUNT_REQUEST = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String MANUAL_LOTTO_NUMBERS_REQUEST = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String INPUT_IS_NOT_NUMBER = "문자열이 아닌 숫자(정수)를 입력해주세요.";
    private static final String DUPLICATE_MANUAL_LOTTO_NUMBERS = "수동 로또 번호 중 중복된 번호가 있습니다.";

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

    public static List<Integer> insertManualLottoNumbers() {
        System.out.println(MANUAL_LOTTO_NUMBERS_REQUEST);
        String manualLottoNumbersInput = Console.readLine();
        List<String> manualLottoNumbers = new ArrayList<>(Arrays.asList(manualLottoNumbersInput.trim().split(",")));
        validateManualLottoNumbers(manualLottoNumbers);

        return manualLottoNumbers.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static void validateManualLottoNumbers(List<String> manualLottoNumbers) {
        manualLottoNumbers.forEach(Input::checkNumber);
        validateDuplicateManualLottoNumbers(manualLottoNumbers);
    }

    private static void validateDuplicateManualLottoNumbers(List<String> manualLottoNumbers) {
        Set<String> uniqueManualLottoNumbers = new HashSet<>(manualLottoNumbers);
        if (uniqueManualLottoNumbers.size() != manualLottoNumbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_MANUAL_LOTTO_NUMBERS);
        }
    }

    private static int checkNumber(String input) {
        if (!input.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException(INPUT_IS_NOT_NUMBER);
        }
        return parseInt(input);
    }
}
