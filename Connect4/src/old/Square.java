package old;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Square extends JLabel {
	
	private int row;
	private int col;
	private int status = 0;
	public Square(int nRow, int nCol)
	{	
		row = nRow;
		col = nCol;
		
		ImageIcon img = new ImageIcon("A:\\shit\\Square.png");
		setIcon(img);
		setSize(100,100);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		
			System.out.println("Clicked:" + row + ":" + col);		
			if (status == 0)
			{
				ImageIcon img = new ImageIcon("A:\\shit\\SquareRed.png");
				status = 1;
				setIcon(img);
			}
			else if (status == 1)
			{
				ImageIcon img = new ImageIcon("A:\\shit\\SquareYell.png");
				status = 2;
				setIcon(img);
			}
			else {
				ImageIcon img = new ImageIcon("A:\\shit\\Square.png");
				status = 0;
				setIcon(img);
			}
			}});

	

	}
	
	public void setColor(int nColor)
	{
		status = nColor;
		
		if (status == 1)
		{
			ImageIcon img = new ImageIcon("A:\\shit\\SquareRed.png");
			status = 1;
			setIcon(img);
		}
		else if (status == 2)
		{
			ImageIcon img = new ImageIcon("A:\\shit\\SquareYell.png");
			status = 2;
			setIcon(img);
		}
		else {
			ImageIcon img = new ImageIcon("A:\\shit\\Square.png");
			status = 0;
			setIcon(img);
		}
		
	}
	
	
	public int getStatus()
	{return status;}
	
	public int getRow()
	{return row;}
	
	public int getCol()
	{return col;}
	
}
