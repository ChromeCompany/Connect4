package connect4;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


public class Board extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8777786990935850086L;
	private Square[] sqrs = new Square[42];
	int sqc;
	
	public int turn=0;
	public boolean winner = false;
	
	public Board()

	{

		setLayout (new GridLayout(0,7));
		
		JLabel end = new JLabel();
		int r=1;
		int c=1;
		
		for (sqc=0; sqc<sqrs.length;sqc++)
		{
			
			if (c>7)
			{r++;c=c-7;}
			
			sqrs[sqc] = new Square(r,c);
			add(sqrs[sqc]);
			sqrs[sqc].setToolTipText(""+sqc);
			sqrs[sqc].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
			
					checkGravity();
					winner = checkVictor();
					
					if (winner == true)
					{
						
						end.setText("GAME OVER.");
						end.setSize(500, 250);
						add(end);
					}
					
				}		
			
			});
			
			c++;
			
		}
		
	}
	
	public void checkGravity()
	{
		//System.out.println("Falling Objects.");
		for (int sqc=0; sqc<sqrs.length;sqc++)
		{
			//System.out.println("SQC;" + sqc);
			for (int nxt=sqc+7;nxt<sqrs.length;nxt=nxt+7)
			{
				//System.out.println("NXT;" + nxt);
				if (sqrs[nxt].getStatus()==0)
				{
					sqrs[nxt].setColor(sqrs[sqc].getStatus());
					sqrs[sqc].setColor(0);
					
				}
			}
			
		}
	}
	
	public boolean checkVictor()
	{
		int vRCount = 0;
		int vYCount = 0;
		boolean x = false;
		//Check Vertical
		for (int sqc=0; sqc<sqrs.length;sqc++)
		{
			if (sqrs[sqc].getStatus() >  0)
			{
			//System.out.println("SQC;" + sqc);
			for (int nxt=sqc+7;nxt<sqrs.length;nxt=nxt+7)
			{
				//System.out.println("NXT;" + nxt);
				if (sqrs[nxt].getStatus()==sqrs[sqc].getStatus())
				{
					if (sqrs[sqc].getStatus() == 1) //Red
					{vRCount++;}
					else if (sqrs[sqc].getStatus() == 2)//Yellow
					{vYCount++;}					
				}
				else {break;}
				
			}
			
			if (vRCount == 3 || vYCount == 3)
			{
				//Game Over.
				System.out.println("V:Game Over.");
				return true;
			}
			
			if (vRCount >0) {System.out.println("Vertical Red Victory Count:" + vRCount);}
			if (vYCount >0) {System.out.println("Vertical Yellow Victory Count:" + vYCount);}
			vRCount = 0;
			vYCount = 0;
			}
			
		}
		//Check Horizontal
		for (int sqc=0; sqc<sqrs.length;sqc++)
		{
			
			if (sqrs[sqc].getStatus() >  0)
			{
			//System.out.println("SQC;" + sqc);
			for (int nxt=sqc+1;nxt<sqrs.length;nxt++)
			{
				if (sqrs[sqc].getRow() != sqrs[nxt].getRow()) {break;} //This will prevent counting victor points if they aren't on the same row.
				//System.out.println("NXT;" + nxt);
				if (sqrs[nxt].getStatus()==sqrs[sqc].getStatus())
				{
					if (sqrs[sqc].getStatus() == 1) //Red
					{vRCount++;}
					else if (sqrs[sqc].getStatus() == 2)//Yellow
					{vYCount++;}					
				}
				else {break;}
				
			}
			
			if (vRCount == 3 || vYCount == 3)
			{
				//Game Over.
				System.out.println("H:Game Over.");
				return true;
			}
			
			if (vRCount >0) {System.out.println("Horizontal Red Victory Count:" + vRCount);}
			if (vYCount >0) {System.out.println("Horizontal Yellow Victory Count:" + vYCount);}
			vRCount = 0;
			vYCount = 0;
			}
			
		}
		//Check Diagonal (L>R)
		for (int sqc=0; sqc<sqrs.length;sqc++)
		{
			
			if (sqrs[sqc].getStatus() >  0)
			{
			//System.out.println("SQC;" + sqc);
			for (int nxt=sqc+8;nxt<sqrs.length;nxt=nxt+8)
			{

				if (sqrs[sqc].getRow() == sqrs[nxt].getRow()) {break;} //This will prevent counting victor points if they are on the same row.
				
				//System.out.println("NXT;" + nxt);
				if (sqrs[nxt].getStatus()==sqrs[sqc].getStatus())
				{
					if (sqrs[sqc].getStatus() == 1) //Red
					{vRCount++;}
					else if (sqrs[sqc].getStatus() == 2)//Yellow
					{vYCount++;}					
				}
				else {break;}
				
			}
			
			if (vRCount == 3 || vYCount == 3)
			{
				//Game Over.
				System.out.println("L>R:Game Over.");
				return true;
			}
			
			if (vRCount >0) {System.out.println("Diagonal (L>R) Red Victory Count:" + vRCount);}
			if (vYCount >0) {System.out.println("Diagonal (L>R) Yellow Victory Count:" + vYCount);}
			vRCount = 0;
			vYCount = 0;
			
			if (sqrs[sqc].getCol()==7)
			{
				break;
			}
			
			}
			
		}
		//Check Diagonal (R>L)
		for (int sqc=0; sqc<sqrs.length;sqc++)
		{
			//if (sqrs[sqc].getCol()<=3) {System.out.println(sqrs[sqc].getCol()+" is out of bounds, can't win here.");break;} // Don't look in the last 3 columns, it will fuck you up.
			if (sqrs[sqc].getStatus() >  0)
			{
			//System.out.println("SQC;" + sqc);
			for (int nxt=sqc+6;nxt<sqrs.length;nxt=nxt+6)
			{
				//if (sqrs[nxt].getCol()<=3) {System.out.println(sqrs[nxt].getCol()+" is out of bounds, can't win here.");break;}
				if (sqrs[sqc].getRow() == sqrs[nxt].getRow()) {break;} //This will prevent counting victor points if they are on the same row.
				//System.out.println("NXT;" + nxt);
				if (sqrs[nxt].getStatus()==sqrs[sqc].getStatus())
				{
					if (sqrs[sqc].getStatus() == 1) //Red
					{vRCount++;}
					else if (sqrs[sqc].getStatus() == 2)//Yellow
					{vYCount++;}					
				}
				else {break;}
				
			}
			
			if (vRCount == 3 || vYCount == 3)
			{
				//Game Over.
				System.out.println("R<L:Game Over.");
				return true;
			}
			
			if (vRCount >0) {System.out.println("Diagonal (L>R) Red Victory Count:" + vRCount);}
			if (vYCount >0) {System.out.println("Diagonal (L>R) Yellow Victory Count:" + vYCount);}
			vRCount = 0;
			vYCount = 0;
			
			if (sqrs[sqc].getCol()==1)
			{
				break;
			}
			
			}
			
		}
		return false;
	}
	
	class Square extends JLabel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 4823781117291338405L;
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
				if (winner == false)
				{
					if (turn == 0)
					{
						ImageIcon img = new ImageIcon("A:\\shit\\SquareRed.png");
						status = 1;
						setIcon(img);
					}
					else if (turn == 1)
					{
						ImageIcon img = new ImageIcon("A:\\shit\\SquareYell.png");
						status = 2;
						setIcon(img);
					}
					
					if (turn == 1) {turn = 0;}else{turn = 1;}
					
				}
				}
				});

		

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
	
}
