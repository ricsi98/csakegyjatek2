package model;

import math.Vec2;

import java.util.List;

public class Entity {
    private Vec2 pos;
    private List<Action> actions;

    public Entity(Vec2 pos) {
        this.pos = pos;
    }

    public Entity(double x, double y) {
        this.pos = new Vec2(x, y);
    }
}
