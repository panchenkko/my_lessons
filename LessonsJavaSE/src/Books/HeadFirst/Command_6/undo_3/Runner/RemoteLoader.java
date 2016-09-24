package Books.HeadFirst.Command_6.Undo_3.Runner;

import Books.HeadFirst.Command_6.Undo_3.Commands.CeilingFan.CeilingFan;
import Books.HeadFirst.Command_6.Undo_3.Commands.CeilingFan.CeilingFanHighCommand;
import Books.HeadFirst.Command_6.Undo_3.Commands.CeilingFan.CeilingFanMediumCommand;
import Books.HeadFirst.Command_6.Undo_3.Commands.CeilingFan.CeilingFanOffCommand;
import Books.HeadFirst.Command_6.Undo_3.Commands.Light.Light;
import Books.HeadFirst.Command_6.Undo_3.Commands.Light.LightOffCommand;
import Books.HeadFirst.Command_6.Undo_3.Commands.Light.LightOnCommand;
import Books.HeadFirst.Command_6.Undo_3.RemoteControlWithUndo;

public class RemoteLoader {
 
	public static void main(String[] args) {
		RemoteControlWithUndo remoteControl = new RemoteControlWithUndo();
 
		Light livingRoomLight = new Light("Living Room");
 
		LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
		LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
 
		remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);
 
		remoteControl.onButtonWasPushed(0); // Вкл свет
		remoteControl.offButtonWasPushed(0); // Выкл свет
		System.out.println(remoteControl);
		remoteControl.undoButtonWasPushed(); // Отмена. Свет должен включиться
		remoteControl.offButtonWasPushed(0); // Выкл свет
		remoteControl.onButtonWasPushed(0); // Вкл свет
		System.out.println(remoteControl);
		remoteControl.undoButtonWasPushed(); // И снова отмена. Свет должен выключиться

		CeilingFan ceilingFan = new CeilingFan("Living Room");

		// Создаем экземпляры трёх команд: для высокой, для средней скорости и для выключения
		CeilingFanMediumCommand ceilingFanMedium = new CeilingFanMediumCommand(ceilingFan);
		CeilingFanHighCommand ceilingFanHigh = new CeilingFanHighCommand(ceilingFan);
		CeilingFanOffCommand ceilingFanOff = new CeilingFanOffCommand(ceilingFan);

		// Помещаем их в ячейки 0 и 1
		remoteControl.setCommand(0, ceilingFanMedium, ceilingFanOff);
		remoteControl.setCommand(1, ceilingFanHigh, ceilingFanOff);
   
		remoteControl.onButtonWasPushed(0); // Вкл среднюю скорость
		remoteControl.offButtonWasPushed(0); // Выкл вентилятор
		System.out.println(remoteControl);
		remoteControl.undoButtonWasPushed(); // Нажимаем Отмену. Снова должна включиться средняя скорость
  
		remoteControl.onButtonWasPushed(1); // Вкл высокую скорость
		System.out.println(remoteControl);
		remoteControl.undoButtonWasPushed(); // И снова Отмена. Должна вернуться средняя скорость
	}
}
