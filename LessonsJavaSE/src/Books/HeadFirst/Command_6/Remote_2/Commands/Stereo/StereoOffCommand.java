package Books.HeadFirst.Command_6.Remote_2.Commands.Stereo;

import Books.HeadFirst.Command_6.Remote_2.Commands.Command;

public class StereoOffCommand implements Command {
	Stereo stereo;
 
	public StereoOffCommand(Stereo stereo) {
		this.stereo = stereo;
	}
 
	public void execute() {
		stereo.off();
	}
}
