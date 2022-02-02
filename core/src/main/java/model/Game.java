package model;

public class Game {

    private final String name;
    private int maxPlayers;
    private Model model;

    public Game(String name, int maxPlayers) {
        this.name = name;
        this.maxPlayers = maxPlayers;
    }

    public void init() {
        if (this.model != null) {
            this.model = new Model();
            Map map = new Map();
            for (int i = 0; i < 10; i++) {
                map.addWall(new Wall(i, 0));
            }
            this.model.setMap(map);
        }
    }

    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    public int getCurrentPlayerCount() {
        return this.model.getPlayers().size();
    }

    public void join(Player player) {
        this.model.getPlayers().add(player);
    }

    public void leave(Player player) {
        this.model.getPlayers().remove(player);
    }

    public String getName() {
        return name;
    }
}
