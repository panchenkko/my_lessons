package Books.HeadFirst.Command_6.Undo_3.Commands.Light.DimmerLight;

import Books.HeadFirst.Command_6.Undo_3.Commands.Command;
import Books.HeadFirst.Command_6.Undo_3.Commands.Light.Light;

public class DimmerLightOnCommand implements Command {
	Light light;
	int prevLevel;

	public DimmerLightOnCommand(Light light) {
		this.light = light;
	}

	public void execute() {
		prevLevel = light.getLevel();
		light.dim(75);
	}

	public void undo() {
		light.dim(prevLevel);
	}
}
