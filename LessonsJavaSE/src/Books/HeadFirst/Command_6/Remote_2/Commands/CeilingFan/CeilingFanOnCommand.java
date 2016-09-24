package Books.HeadFirst.Command_6.Remote_2.Commands.CeilingFan;

import Books.HeadFirst.Command_6.Remote_2.Commands.Command;

public class CeilingFanOnCommand implements Command {
	CeilingFan ceilingFan;

	public CeilingFanOnCommand(CeilingFan ceilingFan) {
		this.ceilingFan = ceilingFan;
	}
	public void execute() {
		ceilingFan.high();
	}
}
