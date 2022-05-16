package nextstep.ladder.model;

import nextstep.ladder.util.InputUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Line {

    private static final String NOT_NULL_CREATE_MESSAGE = "라인은 생성이 되어야 합니다.";
    private static final String CONTINUITY_CREATE_MESSAGE = "연속으로 바를 생성할 수 없습니다.";
    private static final String LAST_CREATE_MESSAGE = "마지막 바는 생성할 수 없습니다.";
    private static final int FIRST_INDEX = 1;

    private final List<Bar> bars;

    public Line(int participantCount) {
        this(createLine(participantCount));
    }

    public Line(List<Boolean> bars) {
        this.bars = convert(bars);
    }

    public static Line from(List<Boolean> bars) {
        validate(bars);
        return new Line(bars);
    }

    private static List<Boolean> createLine(int participantCount) {
        List<Boolean> bars = new ArrayList<>();
        bars.add(InputUtil.randomBar(null));
        for (int i = FIRST_INDEX; i < participantCount - FIRST_INDEX; i++) {
            bars.add(InputUtil.randomBar(bars.get(i - FIRST_INDEX)));
        }
        if (FIRST_INDEX < participantCount) {
            bars.add(false);
        }
        return bars;
    }

    private static List<Bar> convert(List<Boolean> bars) {
        return bars.stream()
                .map(Bar::new)
                .collect(Collectors.toList());
    }

    public int barsCount() {
        return bars.size();
    }

    public List<Bar> bars() {
        return bars;
    }

    private static void validate(List<Boolean> bars) {
        validateNullCheck(convert(bars));
        validateContinuityCreate(bars);
        validateCreateLastBar(bars.get(bars.size() - FIRST_INDEX));
    }

    private static void validateContinuityCreate(List<Boolean> bars) {
        for (int i = FIRST_INDEX; i < bars.size(); i++) {
            compareBars(bars.get(i - FIRST_INDEX), bars.get(i));
        }
    }

    private static void validateCreateLastBar(boolean currentBarStatus) {
        if (currentBarStatus) {
            throw new IllegalArgumentException(LAST_CREATE_MESSAGE);
        }
    }

    private static void compareBars(boolean beforeBarStatus, boolean currentBarStatus) {
        if (beforeBarStatus && currentBarStatus) {
            throw new IllegalArgumentException(CONTINUITY_CREATE_MESSAGE);
        }
    }

    static void validateNullCheck(List<Bar> bars) {
        if (bars.isEmpty()) {
            throw new IllegalArgumentException(NOT_NULL_CREATE_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Objects.equals(bars, line.bars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bars);
    }

}