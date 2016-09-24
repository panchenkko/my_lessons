package Books.HeadFirst.Command_6.SimpleRemote_1.Commands.GarageDoor;

import Books.HeadFirst.Command_6.SimpleRemote_1.Commands.Command;

public class GarageDoorOpenCommand implements Command {
	GarageDoor garageDoor;

	public GarageDoorOpenCommand(GarageDoor garageDoor) {
		this.garageDoor = garageDoor;
	}

	public void execute() {
		garageDoor.up();
	}
}
