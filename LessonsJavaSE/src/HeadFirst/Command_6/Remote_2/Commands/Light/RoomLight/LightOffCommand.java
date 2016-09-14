package HeadFirst.Command_6.Remote_2.Commands.Light.RoomLight;

import HeadFirst.Command_6.Remote_2.Commands.Command;
import HeadFirst.Command_6.Remote_2.Commands.Light.Light;

public class LightOffCommand implements Command {
	Light light;
 
	public LightOffCommand(Light light) {
		this.light = light;
	}
 
	public void execute() {
		light.off();
	}
}
