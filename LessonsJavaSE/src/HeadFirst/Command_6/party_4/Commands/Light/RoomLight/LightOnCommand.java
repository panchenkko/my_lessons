package HeadFirst.Command_6.Party_4.Commands.Light.RoomLight;

import HeadFirst.Command_6.Party_4.Commands.Command;
import HeadFirst.Command_6.Party_4.Commands.Light.Light;

public class LightOnCommand implements Command {
	Light light;

	public LightOnCommand(Light light) {
		this.light = light;
	}

	public void execute() {
		light.on();
	}

	public void undo() {
		light.off();
	}
}
