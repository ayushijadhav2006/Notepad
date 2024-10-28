import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.undo.UndoManager;
import javax.swing.event.UndoableEditListener;
import javax.swing.event.UndoableEditEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Function;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;

public class Notepad implements ActionListener{
	
	JFrame window;
	JTextArea txt;
	JScrollPane scrollPane;
	boolean wordWrapOn = false;
	JMenuBar mbr;
	JMenu mfile, mEdit, mFormat, mColor;
	JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;
	JMenuItem iUndo, iRedo;
	JMenuItem iWrap, iFontArial, iFontCSMS, iFontTNR, iFontSize8, iFontSize12, iFontSize16, iFontSize20, iFontSize24,  iFontSize28;
	JMenu mFont, mFontSize;
	JMenuItem iColor1,iColor2, iColor3;
	JLabel statusLabel;

	Function_File file = new Function_File(this);
	Function_Format format = new Function_Format(this);
	Function_Color color = new Function_Color(this);
	Function_Edit edit = new Function_Edit(this);
	KeyHandler kHandler = new KeyHandler(this);
	
	UndoManager um = new UndoManager();
	
	public static void main (String[] args) {
		new Notepad();
	}
	
	public Notepad(){
		createWindow();
		createTextArea();
		createMenuBar();
		createFileMenu();
		createFormatMenu();
		createEditMenu();
		createColorMenu();
		
		format.selectedFont = "Arial";
		format.createFont(16);
		format.wordWrap();
		color.changeColor("White");
		window.setVisible(true);
		statusLabel = new JLabel("Words: 0, Characters: 0");
		window.add(statusLabel, BorderLayout.SOUTH);
		txt.addCaretListener(e -> updateCount());
	}
	private void updateCount() {
		String text = txt.getText();
		int wordCount = text.isEmpty() ? 0 : text.split("\\s+").length;
		int charCount = text.length();
		statusLabel.setText("Words: " + wordCount + ", Characters: " + charCount);
	}
	
	public void createWindow(){
		window = new JFrame("Notepad");
		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void createTextArea() {
		txt = new JTextArea();
		txt.setFont(format.arial);
		txt.addKeyListener(kHandler);
		txt.getDocument().addUndoableEditListener(
			new UndoableEditListener() {
				public void undoableEditHappened(UndoableEditEvent e) {
					um.addEdit(e.getEdit());
				}
			});
		scrollPane = new JScrollPane(txt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		window.add(scrollPane);
	}
	public void createMenuBar() {
		mbr = new JMenuBar();
		window.setJMenuBar(mbr);
		mfile = new JMenu("File");
		mbr.add(mfile);
		mEdit = new JMenu("Edit");
		mbr.add(mEdit);
		mFormat = new JMenu("Format");
		mbr.add(mFormat);
		mColor = new JMenu("Color");
		mbr.add(mColor);
	}
	public void createFileMenu() {
		iNew = new JMenuItem("New");
		iNew.addActionListener(this);
		iNew.setActionCommand("New");
		mfile.add(iNew);
		iOpen = new JMenuItem("Open");
		iOpen.addActionListener(this);
		iOpen.setActionCommand("Open");
		mfile.add(iOpen);
		iSave = new JMenuItem("Save");
		iSave.addActionListener(this);
		iSave.setActionCommand("Save");
		mfile.add(iSave);
		iSaveAs = new JMenuItem("SaveAs");
		iSaveAs.addActionListener(this);
		iSaveAs.setActionCommand("SaveAs");
		mfile.add(iSaveAs);
		iExit = new JMenuItem("Exit");
		iExit.addActionListener(this);
		iExit.setActionCommand("Exit");
		mfile.add(iExit);
	}
	public void createFormatMenu() {
		iWrap = new JMenuItem("Word Wrap: Off");
		iWrap.addActionListener(this);
		iWrap.setActionCommand("Word Wrap");
		mFormat.add(iWrap);
		mFont = new JMenu("Font");
		mFormat.add(mFont);
		iFontArial = new JMenuItem("Arial");
		iFontArial.addActionListener(this);
		iFontArial.setActionCommand("Arial");
		mFont.add(iFontArial);
		iFontCSMS = new JMenuItem("Comic Sans MS");
		iFontCSMS.addActionListener(this);
		iFontCSMS.setActionCommand("Comic Sans MS");
		mFont.add(iFontCSMS);
		iFontTNR = new JMenuItem("Times New Roman");
		iFontTNR.addActionListener(this);
		iFontTNR.setActionCommand("Times New Roman");
		mFont.add(iFontTNR);
		mFontSize = new JMenu("Font Size");
		mFormat.add(mFontSize);
		iFontSize8 = new JMenuItem("8");
		iFontSize8.addActionListener(this);
		iFontSize8.setActionCommand("size8");
		mFontSize.add(iFontSize8);
		iFontSize12 = new JMenuItem("12");
		iFontSize12.addActionListener(this);
		iFontSize12.setActionCommand("size12");
		mFontSize.add(iFontSize12);
		iFontSize16 = new JMenuItem("16");
		iFontSize16.addActionListener(this);
		iFontSize16.setActionCommand("size16");
		mFontSize.add(iFontSize16);
		iFontSize20 = new JMenuItem("20");
		iFontSize20.addActionListener(this);
		iFontSize20.setActionCommand("size20");
		mFontSize.add(iFontSize20);
		iFontSize28 = new JMenuItem("28");
		iFontSize28.addActionListener(this);
		iFontSize28.setActionCommand("size28");
		mFontSize.add(iFontSize28);
	}
	
	public void createEditMenu() {
		iUndo = new JMenuItem("Undo");
		iUndo.addActionListener(this);
		iUndo.setActionCommand("Undo");
		mEdit.add(iUndo);
		iRedo = new JMenuItem("Redo");
		iRedo.addActionListener(this);
		iRedo.setActionCommand("Redo");
		mEdit.add(iRedo);
		JMenuItem iSearchReplace = new JMenuItem("Search & Replace");
		iSearchReplace.addActionListener(this);
		iSearchReplace.setActionCommand("SearchReplace");
		mEdit.add(iSearchReplace);
		JMenuItem iSearch = new JMenuItem("Search");
		iSearch.addActionListener(this);
		iSearch.setActionCommand("Search");
		mEdit.add(iSearch);
	}
	public void createColorMenu() {
		iColor1 = new JMenuItem("White");
		iColor1.addActionListener(this);
		iColor1.setActionCommand("White");
		mColor.add(iColor1);
		iColor2 = new JMenuItem("Black");
		iColor2.addActionListener(this);
		iColor2.setActionCommand("Black");
		mColor.add(iColor2);
		iColor3 = new JMenuItem("Blue");
		iColor3.addActionListener(this);
		iColor3.setActionCommand("Blue");
		mColor.add(iColor3);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch(command) {
			case "New": file.newFile(); break;
			case "Open": file.open(); break;
			case "Save": file.save(); break;
			case "SaveAs": file.saveAs(); break;
			case "Exit": file.exit(); break;
			case "Undo": edit.undo(); break;
			case "Redo": edit.redo(); break;
			case "Word Wrap": format.wordWrap(); break;
			case "Arial": format.setFont(command); break;
			case "Comic Sans MS": format.setFont(command); break;
			case "Times New Roman": format.setFont(command); break;
			case "size8": format.createFont(8); break;
			case "size12": format.createFont(12); break;
			case "size16": format.createFont(16); break;
			case "size20": format.createFont(20); break;
			case "size28": format.createFont(28); break;
			case "White": color.changeColor(command); break;
			case "Black": color.changeColor(command); break;
			case "Blue": color.changeColor(command); break;
			case "SearchReplace": openSearchReplaceDialog(); break;
			case "Search": openSearchDialog(); break;
		}
	}
	
	private void openSearchReplaceDialog() {
		JDialog dialog = new JDialog(window, "Search & Replace", true);
		dialog.setLayout(new GridLayout(3, 2));
		JTextField searchField = new JTextField();
		JTextField replaceField = new JTextField();
		JButton replaceButton = new JButton("Replace");
		dialog.add(new JLabel("Search:"));
		dialog.add(searchField);
		dialog.add(new JLabel("Replace with:"));
		dialog.add(replaceField);
		dialog.add(replaceButton);
		replaceButton.addActionListener(e -> {
			String searchText = searchField.getText();
			String replaceText = replaceField.getText();
			String content = txt.getText();
			txt.setText(content.replace(searchText, replaceText));
			dialog.dispose();
		});
		dialog.setSize(300, 150);
		dialog.setVisible(true);
	}
	
	private void openSearchDialog() {
		String searchText = JOptionPane.showInputDialog(window, "Enter text to search:");
		if (searchText != null && !searchText.isEmpty()) {
			String content = txt.getText();
			int index = content.indexOf(searchText);
			
			if (index != -1) {
				txt.setCaretPosition(index);
				txt.select(index, index + searchText.length());
				txt.requestFocus();
				highlightAllOccurrences(searchText);
			} else {
				JOptionPane.showMessageDialog(window, "Text not found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	private void highlightAllOccurrences(String searchText) {
		String content = txt.getText();
		int index = content.indexOf(searchText);
		txt.getHighlighter().removeAllHighlights();

		while (index >= 0) {
			try {
				txt.getHighlighter().addHighlight(index, index + searchText.length(), new javax.swing.text.DefaultHighlighter.DefaultHighlightPainter(java.awt.Color.YELLOW));
				index = content.indexOf(searchText, index + searchText.length());
			} catch (javax.swing.text.BadLocationException e) {
				e.printStackTrace();
			}
		}
	}
}
