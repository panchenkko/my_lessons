package HeadFirst.Command_6.Undo_3.Commands.Light.DimmerLight;

import HeadFirst.Command_6.Undo_3.Commands.Command;
import HeadFirst.Command_6.Undo_3.Commands.Light.Light;

public class DimmerLightOffCommand implements Command {
	Light light;
	int prevLevel;

	public DimmerLightOffCommand(Light light) {
		this.light = light;
		prevLevel = 100;
	}

	public void execute() {
		prevLevel = light.getLevel();
		light.off();
	}

	public void undo() {
		light.dim(prevLevel);
	}
}
