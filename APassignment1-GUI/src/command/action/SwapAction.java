package command.action;

import command.Action;

public class SwapAction implements Action {

    private String name;


    public SwapAction(String name){
        this.name=name;
    }

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
    
    @Override
    public void redo() {
    	
    }

    @Override
    public String getName() {
        return this.name;
    }
}
