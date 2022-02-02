package model;

import java.util.List;

public class Map {

    private List<Wall> walls;
    private Model model;

    public void addWall(Wall w) {
        this.walls.add(w);
    }

    public void setModel(Model m) {
        this.model = m;
        for (Wall w : this.walls) {
            m.addEntity(w);
        }
    }

}
