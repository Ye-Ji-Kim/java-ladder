package nextstep.ladder.entity.ladder;

import java.util.Objects;

/**
 * 사다리의 각 지점을 추상화
 * 가로 방향으로 연결되어 Link를 통해 연결되었는지를 나타냄 
 */
public class Point {

    private int position;
    private Point next;
    private Point linkWith;

    private Point(int position) {
        this.position = position;
    }

    public static Point start() {
        return new Point(1);
    }

    public static Point of(int value) {
        return new Point(value);
    }

    public void next(Point next) {
        this.next = next;
    }

    public boolean isLinked() {
        return Objects.nonNull(linkWith);
    }

    public boolean hasNext() {
        return Objects.nonNull(next);
    }

    public boolean hasBackwardLink() {
        return Objects.nonNull(linkWith) && !Objects.equals(next, linkWith);
    }

    public void linkWithNext() {
        if (hasNext()) {
            this.linkWith = next;
            next.linkWith = this;
        }
    }

    public Point getNext() {
        return this.next;
    }

    public int getPosition() {
        return position;
    }

    public Point getLinkPoint() {
        return linkWith;
    }

    public boolean isOnPositionOf(int position) {
        return this.position == position;
    }
}