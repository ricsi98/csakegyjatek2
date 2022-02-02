package model;

public interface Action {

    public boolean canExecute(Entity source, Entity target);
    public void execute(Entity source, Entity target);

}
