package Books.HeadFirst.Command_6.Remote_2.Commands.Hottub;

import Books.HeadFirst.Command_6.Remote_2.Commands.Command;

public class HottubOffCommand implements Command {
	Hottub hottub;

	public HottubOffCommand(Hottub hottub) {
		this.hottub = hottub;
	}

	public void execute() {
		hottub.cool();
		hottub.off();
	}
}
