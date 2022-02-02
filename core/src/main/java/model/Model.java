package model;

import java.util.List;

public class Model {

    private Map map;
    private List<Player> players;
    private List<Entity> entities;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
        map.setModel(this);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }



}
