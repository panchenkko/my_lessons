package Books.HeadFirst.Command_6.Remote_2.Runner;

import Books.HeadFirst.Command_6.Remote_2.*;
import Books.HeadFirst.Command_6.Remote_2.Commands.CeilingFan.CeilingFan;
import Books.HeadFirst.Command_6.Remote_2.Commands.CeilingFan.CeilingFanOffCommand;
import Books.HeadFirst.Command_6.Remote_2.Commands.CeilingFan.CeilingFanOnCommand;
import Books.HeadFirst.Command_6.Remote_2.Commands.GarageDoor.GarageDoor;
import Books.HeadFirst.Command_6.Remote_2.Commands.GarageDoor.GarageDoorDownCommand;
import Books.HeadFirst.Command_6.Remote_2.Commands.GarageDoor.GarageDoorUpCommand;
import Books.HeadFirst.Command_6.Remote_2.Commands.Light.Light;
import Books.HeadFirst.Command_6.Remote_2.Commands.Light.RoomLight.LightOffCommand;
import Books.HeadFirst.Command_6.Remote_2.Commands.Light.RoomLight.LightOnCommand;
import Books.HeadFirst.Command_6.Remote_2.Commands.Stereo.Stereo;
import Books.HeadFirst.Command_6.Remote_2.Commands.Stereo.StereoOffCommand;
import Books.HeadFirst.Command_6.Remote_2.Commands.Stereo.StereoOnWithCDCommand;

public class RemoteLoader {
 
	public static void main(String[] args) {
		RemoteControl remoteControl = new RemoteControl();

		// Создание всех устройств
		Light livingRoomLight = new Light("Living Room");
		Light kitchenLight = new Light("Kitchen");
		CeilingFan ceilingFan= new CeilingFan("Living Room");
		GarageDoor garageDoor = new GarageDoor("");
		Stereo stereo = new Stereo("Living Room");

		// Создание комманд для управления освещением
		LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
		LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
		LightOnCommand kitchenLightOn = new LightOnCommand(kitchenLight);
		LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);

		// Создание комманд для управления потолочным вентилятором
		CeilingFanOnCommand ceilingFanOn = new CeilingFanOnCommand(ceilingFan);
		CeilingFanOffCommand ceilingFanOff = new CeilingFanOffCommand(ceilingFan);

		// Создание комманд для управления дверью гаража
		GarageDoorUpCommand garageDoorUp = new GarageDoorUpCommand(garageDoor);
		GarageDoorDownCommand garageDoorDown = new GarageDoorDownCommand(garageDoor);

		// Создание комманд для управления стереосистемой
		StereoOnWithCDCommand stereoOnWithCD = new StereoOnWithCDCommand(stereo);
		StereoOffCommand stereoOff = new StereoOffCommand(stereo);

		// Готовые комманды связываются с ячейками пульта
		remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);
		remoteControl.setCommand(1, kitchenLightOn, kitchenLightOff);
		remoteControl.setCommand(2, ceilingFanOn, ceilingFanOff);
		remoteControl.setCommand(3, stereoOnWithCD, stereoOff);

		// Метод toString()
		System.out.println(remoteControl);

		// Пульт готов к проверке!
		// Перебираем все ячейки, и для каждой ячейки имитируем нажатие кнопок "вкл" и "выкл".
		remoteControl.onButtonWasPushed(0);
		remoteControl.offButtonWasPushed(0);
		remoteControl.onButtonWasPushed(1);
		remoteControl.offButtonWasPushed(1);
		remoteControl.onButtonWasPushed(2);
		remoteControl.offButtonWasPushed(2);
		remoteControl.onButtonWasPushed(3);
		remoteControl.offButtonWasPushed(3);
	}
}
