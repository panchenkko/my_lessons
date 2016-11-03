package Books.HeadFirst.Command_6.Party_4.Commands.Light.LivingRoomLight;

import Books.HeadFirst.Command_6.Party_4.Commands.Command;
import Books.HeadFirst.Command_6.Party_4.Commands.Light.Light;

public class LivingroomLightOffCommand implements Command {
	Light light;

	public LivingroomLightOffCommand(Light light) {
		this.light = light;
	}
	public void execute() {
		light.off();
	}
	public void undo() {
		light.on();
	}
}
