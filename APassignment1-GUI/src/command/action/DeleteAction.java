package command.action;

import command.Action;

public class DeleteAction implements Action {

    private String name;


    public DeleteAction(String name){
        this.name=name;
    }

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }

    @Override
    public String getName() {
        return this.name;
    }
}
