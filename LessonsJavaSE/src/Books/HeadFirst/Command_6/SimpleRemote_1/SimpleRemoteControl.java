package Books.HeadFirst.Command_6.SimpleRemote_1;

import Books.HeadFirst.Command_6.SimpleRemote_1.Commands.Command;

/**
 * Вызывающий
 */
public class SimpleRemoteControl {
	Command slot;
 
	public SimpleRemoteControl() {}
 
	public void setCommand(Command command) {
		slot = command;
	}
 
	public void buttonWasPressed() {
		slot.execute();
	}
}
