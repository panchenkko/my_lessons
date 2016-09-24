package Books.HeadFirst.Command_6.Remote_2.Commands.Light.LivingRoomLight;

import Books.HeadFirst.Command_6.Remote_2.Commands.Command;
import Books.HeadFirst.Command_6.Remote_2.Commands.Light.Light;

public class LivingroomLightOnCommand implements Command {
	Light light;

	public LivingroomLightOnCommand(Light light) {
		this.light = light;
	}

	public void execute() {
		light.on();
	}
}
