package HeadFirst.Command_6.SimpleRemote_1.Runner;

import HeadFirst.Command_6.SimpleRemote_1.Commands.GarageDoor.GarageDoor;
import HeadFirst.Command_6.SimpleRemote_1.Commands.GarageDoor.GarageDoorOpenCommand;
import HeadFirst.Command_6.SimpleRemote_1.Commands.Light.Light;
import HeadFirst.Command_6.SimpleRemote_1.Commands.Light.LightOnCommand;
import HeadFirst.Command_6.SimpleRemote_1.SimpleRemoteControl;

public class RemoteControlTest {
	public static void main(String[] args) {
		SimpleRemoteControl remote = new SimpleRemoteControl();

		Light light = new Light();
		GarageDoor garageDoor = new GarageDoor();

		LightOnCommand lightOn = new LightOnCommand(light);
		GarageDoorOpenCommand garageOpen = new GarageDoorOpenCommand(garageDoor);
 
		remote.setCommand(lightOn);
		remote.buttonWasPressed();
		remote.setCommand(garageOpen);
		remote.buttonWasPressed();
    }
}
