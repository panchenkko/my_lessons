package Books.HeadFirst.Command_6.Party_4.Commands.TV;

import Books.HeadFirst.Command_6.Party_4.Commands.Command;

public class TVOnCommand implements Command {
	TV tv;

	public TVOnCommand(TV tv) {
		this.tv= tv;
	}

	public void execute() {
		tv.on();
		tv.setInputChannel();
	}

	public void undo() {
		tv.off();
	}
}
