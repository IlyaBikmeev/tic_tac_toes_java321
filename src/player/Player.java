package player;

//POJO - Plain Old Java Object
public class Player {
    private final String name;
    private final PlayerType type;

    public Player(String name, PlayerType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public PlayerType getType() {
        return type;
    }
}
