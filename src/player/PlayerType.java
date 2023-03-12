package player;

public enum PlayerType {
    CROSSES("X"), NOUGHTS("O");
    private final String value;

    PlayerType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
