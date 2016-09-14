package HeadFirst.Command_6.Remote_2.Commands.Stereo;

import HeadFirst.Command_6.Remote_2.Commands.Command;

public class StereoOffCommand implements Command {
	Stereo stereo;
 
	public StereoOffCommand(Stereo stereo) {
		this.stereo = stereo;
	}
 
	public void execute() {
		stereo.off();
	}
}
