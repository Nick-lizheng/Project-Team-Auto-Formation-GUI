package command;

import javafx.scene.control.CheckBox;
import model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//singleton
public class CommandManager {
	private static final CommandManager instance = new CommandManager();
	private final Stack<Action> stackNormal;
	private final Stack<Action> stackReverse;
	private final List<String> actionHistory;

	public static CommandManager getInstance() {
		if (instance != null)
			return instance;
		return new CommandManager();
	}

	private CommandManager() {
		stackNormal = new Stack<>();
		stackReverse = new Stack<>();
		actionHistory = new ArrayList<>();
	}

	public void execute(Action action) {
		action.execute();
		stackNormal.push(action);
		actionHistory.add(action.getName());
	}

	public void undo() {
		Action a;
		if (stackNormal.size() > 0) {
			a = stackNormal.pop();
			a.undo();
			stackReverse.push(a);
			actionHistory.add(a.getName() + " - undo");
		}
	}

	public void redo() {
		Action a = stackReverse.pop();
		if (a != null) {
			a.execute();
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