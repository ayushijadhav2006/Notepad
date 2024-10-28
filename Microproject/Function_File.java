import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Function_File {

	Notepad ntp;
	String fileName;
	String fileAddress;
	
	public Function_File(Notepad ntp) {
		
		this.ntp = ntp;
	}
	
	public void newFile() {
		ntp.txt.setText("");
		ntp.window.setTitle("New");
		fileName = null;
		fileAddress = null;
	}
	public void open() {
		FileDialog fd = new FileDialog(ntp.window, "Open", FileDialog.LOAD);
		fd.setVisible(true);
		
		if(fd.getFile()!=null) {
			fileName = fd.getFile();
			fileAddress = fd.getDirectory();
			ntp.window.setTitle(fileName);
		}
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileAddress+ fileName));
			
			ntp.txt.setText("");
			String line = null;
			
			while((line = br.readLine())!=null) {
				ntp.txt.append(line + "\n");
			}
			br.close();
		} catch(Exception e) {
			System.out.println("File not opened");
		}
	}
	public void save() {
		if(fileName==null) {
			saveAs();
		}
		else {
			try {
				FileWriter fw = new FileWriter(fileAddress + fileName);
				fw.write(ntp.txt.getText());
				ntp.window.setTitle(fileName);
				fw.close();
			} catch (Exception e) {
				
				System.out.println("Something Wrong!");
			}
			
		}
	}
	public void saveAs() {
		FileDialog fd = new FileDialog(ntp.window, "Save", FileDialog.SAVE);
		fd.setVisible(true);
		
		if(fd.getFile()!=null) {
			fileName = fd.getFile();
			fileAddress = fd.getDirectory();
			ntp.window.setTitle(fileName);
		}
		
		try {
			FileWriter fw = new FileWriter(fileAddress + fileName);
			fw.write(ntp.txt.getText());
			fw.close();
			
		} catch(Exception e) {
			
			System.out.println("Something Wrong!");
		}
	}
	public void exit() {
		System.exit(0);
	}
}