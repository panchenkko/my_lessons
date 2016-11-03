package Books.HeadFirst.Command_6.Party_4.Commands.Light.RoomLight;

import Books.HeadFirst.Command_6.Party_4.Commands.Command;
import Books.HeadFirst.Command_6.Party_4.Commands.Light.Light;

public class LightOffCommand implements Command {
	Light light;

	public LightOffCommand(Light light) {
		this.light = light;
	}

	public void execute() {
		light.off();
	}

	public void undo() {
		light.on();
	}
}
