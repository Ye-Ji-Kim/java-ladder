package nextstep.ladder.domain.player;

import nextstep.ladder.domain.exception.PlayerNameLengthLimitException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static nextstep.ladder.domain.player.Player.NAME_LENGTH_LIMIT;

class PlayerTest {
    @Test
    void initException() {
        Assertions.assertThatThrownBy(() -> {
                      Player.from("pobiii");
                  }).isInstanceOf(PlayerNameLengthLimitException.class)
                  .hasMessage(String.format("참가자 이름의 길이는 %s를 넘을 수 없습니다.", NAME_LENGTH_LIMIT));
    }

    @Test
    void getName() {
        String name = "pobi";
        Player pobi = Player.from(name);
        Assertions.assertThat(pobi.getName()).isEqualTo(name);
    }
}
