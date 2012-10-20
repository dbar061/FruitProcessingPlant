package console;

/**
 * @file TextAreaOutputStream.java
 * @author Devin Barry
 * @date 01/10/2012
 * @lastModification 12/10/2012
 *  
 * TextAreaOutput stream creates an output stream that prints to
 * a JTextArea. I have overridden the two required write methods
 * for an OutputStream as well as implemented the other write
 * method. All these write methods call append on the JTextArea.
 */

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.io.OutputStream;
import java.io.IOException;

public class TextAreaOutputStream extends OutputStream {
	
	private JTextArea textArea;
	
	public TextAreaOutputStream(JTextArea jta) {
		this.textArea = jta;
		//textArea.append("TextAreaOutputStream created\n");
	}
	
	private void updateTextArea(final String text) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				textArea.append(text);
			}
		});
	}
	
	public void write(int b) throws IOException {
		updateTextArea(String.valueOf((char) b));
	}
	
	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		updateTextArea(new String(b, off, len));
	}
	
	@Override
	public void write(byte[] b) throws IOException {
		write(b, 0, b.length);
	}
	
	
}