package HeadFirst.Command_6.Remote_2.Commands.Light.LivingRoomLight;

import HeadFirst.Command_6.Remote_2.Commands.Command;
import HeadFirst.Command_6.Remote_2.Commands.Light.Light;

public class LivingroomLightOffCommand implements Command {
	Light light;

	public LivingroomLightOffCommand(Light light) {
		this.light = light;
	}

	public void execute() {
		light.off();
	}
}
