package HeadFirst.Command_6.Party_4.Commands.Hottub;

import HeadFirst.Command_6.Party_4.Commands.Command;

public class HottubOnCommand implements Command {
	Hottub hottub;

	public HottubOnCommand(Hottub hottub) {
		this.hottub = hottub;
	}
	public void execute() {
		hottub.on();
		hottub.setTemperature(104);
		hottub.circulate();
	}
	public void undo() {
		hottub.off();
	}
}
