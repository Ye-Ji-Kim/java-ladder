package ladderrefactoring.domain.ladder;
import ladderrefactoring.exception.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PointTest {

    @Test
    @DisplayName("Point 생성")
    void create() {
        // when
        Point point = new Point(2, false, false);
        // then
        assertThat(point).isEqualTo(new Point(2, false, false));
    }

    @Test
    @DisplayName("Point 생성 실패 : true, true")
    void create_fail() {
        // when, then
        assertThatThrownBy(() -> new Point(2, true, true))
                .isInstanceOf(CustomException.class);
    }

    @ParameterizedTest(name = "Point 이동 [{index}] {0} {1} {2} -> {3}")
    @CsvSource({"2, false, false, 2", "2, true, false, 1", "2, false, true, 3"})
    void move(int index, boolean left, boolean right, int result) {
        // when
        Point point = new Point(index, left, right);
        // then
        assertThat(point.move()).isEqualTo(result);
    }

}