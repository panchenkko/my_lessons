package HeadFirst.Command_6.Party_4.Commands.TV;

import HeadFirst.Command_6.Party_4.Commands.Command;

public class TVOffCommand implements Command {
	TV tv;

	public TVOffCommand(TV tv) {
		this.tv= tv;
	}

	public void execute() {
		tv.off();
	}

	public void undo() {
		tv.on();
	}
}
