package controller;

import model.*;

public interface GameController {

    public void init();

    public void addPlayer(Player p);

    public void removePlayer(Player p);

    public boolean executeAction(Action action);

}
