package HeadFirst.Command_6.Party_4.Commands.Light.LivingRoomLight;

import HeadFirst.Command_6.Party_4.Commands.Command;
import HeadFirst.Command_6.Party_4.Commands.Light.Light;

public class LivingroomLightOnCommand implements Command {
	Light light;

	public LivingroomLightOnCommand(Light light) {
		this.light = light;
	}
	public void execute() {
		light.on();
	}
	public void undo() {
		light.off();
	}
}
