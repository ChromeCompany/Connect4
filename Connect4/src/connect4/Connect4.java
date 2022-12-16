package connect4;

import java.awt.EventQueue;
import javax.swing.JFrame;


public class Connect4 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2648081495250357889L;
	public static Board pf = new Board();
	public static int tickTime = 2000;

	public int tick = 0;
	
	public Connect4()  
	{
		initUI();
	}
	
	private void initUI() {
		
		add(pf);
		setSize(720,630);		
		setTitle("Connect 4");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public static void main (String[] args) {
		EventQueue.invokeLater(() -> {
		Connect4 ex = new Connect4();
		long timer = 0;
		ex.setVisible(true);

		//while (1==1)
		//{
			if (System.currentTimeMillis()-timer > tickTime) {
				pf.checkGravity();
				timer = System.currentTimeMillis();
			}
		//}
		
		});

	}
}
