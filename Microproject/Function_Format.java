import java.awt.Font;

public class Function_Format {
	Notepad ntp;
	Font arial, comicSansMS, timesNewRoman;
	String selectedFont;
	
	public Function_Format(Notepad ntp) {
			this.ntp = ntp;
	}
	
	public void wordWrap() {
		if(ntp.wordWrapOn==false) {
			ntp.wordWrapOn=true;
			ntp.txt.setLineWrap(true);
			ntp.txt.setWrapStyleWord(true);
			ntp.iWrap.setText("Word Wrap: On");
		}
		else if(ntp.wordWrapOn==true) {
			ntp.wordWrapOn=false;
			ntp.txt.setLineWrap(false);
			ntp.txt.setWrapStyleWord(false);
			ntp.iWrap.setText("Word Wrap: Off");
		}
	}
	
	public void createFont(int fontSize) {
		arial = new Font("Arial", Font.PLAIN, fontSize);
		comicSansMS = new Font("Comic Sans MS", Font.PLAIN, fontSize);
		timesNewRoman = new Font("Times New Roman", Font.PLAIN, fontSize);
		
		setFont(selectedFont);
	}
	
	public void setFont(String font) {
		selectedFont = font;
		
		switch(selectedFont) {
			case "Arial":
				ntp.txt.setFont(arial);
				break;
			case "Comic Sans MS":
				ntp.txt.setFont(comicSansMS);
				break;
			case "Times New Roman":
				ntp.txt.setFont(timesNewRoman);
				break;
		}
	}
}