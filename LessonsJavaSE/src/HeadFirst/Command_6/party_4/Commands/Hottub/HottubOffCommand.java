package HeadFirst.Command_6.Party_4.Commands.Hottub;

import HeadFirst.Command_6.Party_4.Commands.Command;

public class HottubOffCommand implements Command {
	Hottub hottub;

	public HottubOffCommand(Hottub hottub) {
		this.hottub = hottub;
	}

	public void execute() {
		hottub.setTemperature(98);
		hottub.off();
	}
	public void undo() {
		hottub.on();
	}
}
