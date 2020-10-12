package command;

public interface Action {
    void execute();
    void undo();
    void redo();
    String getName();
}
