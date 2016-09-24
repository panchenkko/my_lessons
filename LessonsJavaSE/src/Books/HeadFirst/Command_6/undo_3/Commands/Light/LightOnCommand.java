package Books.HeadFirst.Command_6.Undo_3.Commands.Light;

import Books.HeadFirst.Command_6.Undo_3.Commands.Command;

public class LightOnCommand implements Command {
	Light light;
	int level;
	public LightOnCommand(Light light) {
		this.light = light;
	}
 
	public void execute() {
        level = light.getLevel();
		light.on();
	}
 
	public void undo() {
		light.dim(level);
	}
}
