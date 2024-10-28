
public class Function_Edit {
	Notepad ntp;
	
	public Function_Edit(Notepad ntp) {
		this.ntp = ntp;
	}
	
	public void undo() {
		ntp.um.undo();
	}
	
	public void redo() {
		ntp.um.redo();
	}
}