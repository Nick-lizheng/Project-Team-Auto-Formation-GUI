package command;

import javafx.scene.control.CheckBox;
import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class CommandManager {
    private static CommandManager instance = new CommandManager();
    private Stack<Action> stackNormal;
    private Stack<Action> stackReverse;
    private List<String> actionHistory;
    public static CommandManager getInstance(){
        if(instance != null)
            return instance;
        return new CommandManager();
    }
    private CommandManager() {
        stackNormal = new Stack<>();
        stackReverse = new Stack<>();
        actionHistory = new ArrayList<>();
    }
    public void execute(Action action){
        action.execute();
        stackNormal.push(action);
        actionHistory.add(action.getName());
    }

    public void undo() {
        Action a;
    	    if (stackNormal.size() > 0)
    	    {   a = stackNormal.pop();
            a.undo();
            stackReverse.push(a);
            actionHistory.add(a.getName() + " - undo");
        }
    }
    public void redo() {
        Action a = stackReverse.pop();
        if ( a != null)
        	{   a.execute();
            stackNormal.push(a);
            actionHistory.add(a.getName() + " - redo");
        }
    }
    public void clearNormal() {
        stackNormal.clear();
    }
    public void clearReverse() {
       stackReverse.clear();
    }
    public List<String> getActionHistory() {
        return actionHistory;
    }
}