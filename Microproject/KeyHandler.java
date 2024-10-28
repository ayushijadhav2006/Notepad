import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	Notepad ntp;
	
	public KeyHandler(Notepad ntp) {
		this.ntp = ntp;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_S) {
			ntp.file.save();
		}
		if(e.isShiftDown() && e.isControlDown() && e.getKeyCode()==KeyEvent.VK_S) {
			ntp.file.saveAs();
		}
		if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_N) {
			ntp.file.newFile();
		}
		if(e.isAltDown() && e.getKeyCode()==KeyEvent.VK_F) {
			ntp.mfile.doClick();
		}
		if(e.isAltDown() && e.getKeyCode()==KeyEvent.VK_E) {
			ntp.mEdit.doClick();
		}
		if(e.isControlDown() && e.isAltDown() && e.getKeyCode()==KeyEvent.VK_F) {
			ntp.mFormat.doClick();
		}
		if(e.isAltDown() && e.getKeyCode()==KeyEvent.VK_C) {
			ntp.mColor.doClick();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}