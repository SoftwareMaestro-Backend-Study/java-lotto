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
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final String LOTTO_NUMBER_OUT_OF_RANGE = "로또 번호는 1부터 45사이의 숫자여야 합니다.";
    private static final String BONUS_WINNING_NUMBER_REQUEST = "보너스 볼을 입력해 주세요.";
    private static final String NORMAL_WINNING_NUMBERS_REQUEST = "지난 주 당첨 번호를 입력해 주세요.";

    public static int insertLottoMoney() {
        System.out.println(MONEY_REQUEST);
        String lottoMoney = Console.readLine();

        return checkNumber(lottoMoney);
    }

    public static int insertManualLottoCount() {
        System.out.println(MANUAL_LOTTO_COUNT_REQUEST);
        String manualLottoCount = Console.readLine();

        return validateNumber(manualLottoCount);
    }

    public static List<Integer> insertLottoNumbers() {
        String lottoNumbersInput = Console.readLine();
        List<String> lottoNumbers = Arrays.asList(lottoNumbersInput.split("\\s*,\\s*"));
        validateLottoNumbers(lottoNumbers);

        return lottoNumbers.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<Integer> insertNormalWinningNumbers() {
        System.out.println(NORMAL_WINNING_NUMBERS_REQUEST);
        return insertLottoNumbers();
    }

    public static Integer insertBonusWinningNumber() {
        System.out.println(BONUS_WINNING_NUMBER_REQUEST);
        String lottoNumber = Console.readLine();

        return validateNumber(lottoNumber);
    }

    private static void validateLottoNumbers(List<String> lottoNumbers) {
        lottoNumbers.forEach(Input::validateNumber);
        validateDuplicateLottoNumbers(lottoNumbers);
    }

    private static void validateDuplicateLottoNumbers(List<String> lottoNumbers) {
        Set<String> uniqueLottoNumbers = new HashSet<>(lottoNumbers);
        if (uniqueLottoNumbers.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_LOTTO_NUMBERS);
        }
    }

    private static int validateNumber(String input) {
        return checkRange(checkNumber(input));
    }

    private static int checkNumber(String input) {
        if (!input.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException(INPUT_IS_NOT_NUMBER);
        }

        return parseInt(input);
    }

    private static int checkRange(int input) {
        if (input < LOTTO_MIN_NUMBER || input > LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException(LOTTO_NUMBER_OUT_OF_RANGE);
        }
        return input;
    }
}
