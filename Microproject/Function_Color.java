import java.awt.Color;

public class Function_Color {
	Notepad ntp;
	
	public Function_Color(Notepad ntp) {
		this.ntp = ntp;
	}
	
	public void changeColor(String color) {
		switch(color) {
			case "White":
				ntp.window.getContentPane().setBackground(Color.white);
				ntp.txt.setBackground(Color.white);
				ntp.txt.setForeground(Color.black);
				break;
			
			case "Black":
				ntp.window.getContentPane().setBackground(Color.black);
				ntp.txt.setBackground(Color.black);
				ntp.txt.setForeground(Color.white);
				break;
				
			case "Blue":
				ntp.window.getContentPane().setBackground(Color.blue);
				ntp.txt.setBackground(Color.blue);
				ntp.txt.setForeground(Color.white);
				break;
		}
	}
}