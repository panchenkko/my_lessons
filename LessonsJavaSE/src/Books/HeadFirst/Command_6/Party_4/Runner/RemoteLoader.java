package Books.HeadFirst.Command_6.Party_4.Runner;

import Books.HeadFirst.Command_6.Party_4.Commands.Command;
import Books.HeadFirst.Command_6.Party_4.Commands.DynamicCommands.MacroCommand;
import Books.HeadFirst.Command_6.Party_4.Commands.Hottub.Hottub;
import Books.HeadFirst.Command_6.Party_4.Commands.Hottub.HottubOffCommand;
import Books.HeadFirst.Command_6.Party_4.Commands.Hottub.HottubOnCommand;
import Books.HeadFirst.Command_6.Party_4.Commands.Light.Light;
import Books.HeadFirst.Command_6.Party_4.Commands.Light.RoomLight.LightOffCommand;
import Books.HeadFirst.Command_6.Party_4.Commands.Light.RoomLight.LightOnCommand;
import Books.HeadFirst.Command_6.Party_4.Commands.Stereo.Stereo;
import Books.HeadFirst.Command_6.Party_4.Commands.Stereo.StereoOffCommand;
import Books.HeadFirst.Command_6.Party_4.Commands.Stereo.StereoOnCommand;
import Books.HeadFirst.Command_6.Party_4.Commands.TV.TV;
import Books.HeadFirst.Command_6.Party_4.Commands.TV.TVOffCommand;
import Books.HeadFirst.Command_6.Party_4.Commands.TV.TVOnCommand;
import Books.HeadFirst.Command_6.Party_4.RemoteControl;

public class RemoteLoader {

	public static void main(String[] args) {

		RemoteControl remoteControl = new RemoteControl();

		Light light = new Light("Living Room");
		TV tv = new TV("Living Room");
		Stereo stereo = new Stereo("Living Room");
		Hottub hottub = new Hottub();
 
		LightOnCommand lightOn = new LightOnCommand(light);
		StereoOnCommand stereoOn = new StereoOnCommand(stereo);
		TVOnCommand tvOn = new TVOnCommand(tv);
		HottubOnCommand hottubOn = new HottubOnCommand(hottub);
		LightOffCommand lightOff = new LightOffCommand(light);
		StereoOffCommand stereoOff = new StereoOffCommand(stereo);
		TVOffCommand tvOff = new TVOffCommand(tv);
		HottubOffCommand hottubOff = new HottubOffCommand(hottub);

		Command[] partyOn = { lightOn, stereoOn, tvOn, hottubOn};
		Command[] partyOff = { lightOff, stereoOff, tvOff, hottubOff};
  
		MacroCommand partyOnMacro = new MacroCommand(partyOn);
		MacroCommand partyOffMacro = new MacroCommand(partyOff);
 
		remoteControl.setCommand(0, partyOnMacro, partyOffMacro);
  
		System.out.println(remoteControl);
		System.out.println("--- Pushing Macro On---");
		remoteControl.onButtonWasPushed(0);
		System.out.println("--- Pushing Macro Off---");
		remoteControl.offButtonWasPushed(0);
	}
}
