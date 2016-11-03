package Books.HeadFirst.Command_6.SimpleRemote_1.Commands.Light;

import Books.HeadFirst.Command_6.SimpleRemote_1.Commands.Command;

public class LightOffCommand implements Command {
	Light light;
 
	public LightOffCommand(Light light) {
		this.light = light;
	}
 
	public void execute() {
		light.off();
	}
}
