package nextstep.ladder.view.exception;

public class InvalidParseIntegerException extends NumberFormatException {

    private static final String MESSAGE = "정수로 변환할 수 없는 입력 값 입니다.";

    public InvalidParseIntegerException() {
        super(MESSAGE);
    }
}