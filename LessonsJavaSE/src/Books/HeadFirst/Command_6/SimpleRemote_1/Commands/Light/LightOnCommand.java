package Books.HeadFirst.Command_6.SimpleRemote_1.Commands.Light;

import Books.HeadFirst.Command_6.SimpleRemote_1.Commands.Command;

public class LightOnCommand implements Command {
	Light light;
  
	public LightOnCommand(Light light) {
		this.light = light;
	}
 
	public void execute() {
		light.on();
	}
}
