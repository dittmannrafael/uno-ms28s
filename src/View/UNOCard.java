package View;
/*
Code created by Josh Braza 
*/
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Interfaces.CardInterface;
import Interfaces.UNOConstants_Original;

@SuppressWarnings("serial")
public abstract class UNOCard extends JPanel implements CardInterface, UNOConstants_Original {
	
	private Color cardColor = null;
	private String value = null;
	private int type = 0;
	private boolean showValue = false;
	
	private Border defaultBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.white, Color.gray);
	private Border focusedBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.black, Color.gray);
	
	public UNOCard(){
	}
	
	public void setShowValue(boolean showValue) {
		this.showValue = showValue;
		this.repaint();
	}
	
	public UNOCard(Color cardColor, int cardType, String cardValue){
		this.cardColor = cardColor;
		this.type = cardType;
		this.value = cardValue;
		this.showValue = true;
		
		this.setPreferredSize(CARDSIZE);
		this.setBorder(defaultBorder);
		
		this.addMouseListener(new MouseHandler());
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		if(showValue) {
			paintCardFront(g2);
		} else {
			paintCardBack(g2);
		}	
	}	
	
	private void paintCardFront(Graphics2D g) {
		int cardWidth = CARDSIZE.width;
		int cardHeight = CARDSIZE.height;
		
		//Paints the external border of the card
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, cardWidth, cardHeight);
		
		//Paints the color of the card
		int margin = 5;
		g.setColor(cardColor);
		g.fillRect(margin, margin, cardWidth-2*margin, cardHeight-2*margin);
		
		//Paints the oval format in the center of the card
		g.setColor(Color.white);
		AffineTransform org = g.getTransform();
		g.rotate(45,cardWidth*3/4,cardHeight*3/4);		

		g.fillOval(0,cardHeight*1/4,cardWidth*3/5, cardHeight);
		g.setTransform(org);		
		
		//Value in the center		
		Font defaultFont = new Font("Helvetica", Font.BOLD, cardWidth/2+5);		
		FontMetrics fm = this.getFontMetrics(defaultFont);
		int StringWidth = fm.stringWidth(value)/2;
		int FontHeight = defaultFont.getSize()*1/3;
		
		g.setColor(cardColor);
		g.setFont(defaultFont);
		g.drawString(value, cardWidth/2-StringWidth, cardHeight/2+FontHeight);
		
		//Value in the corner
		defaultFont = new Font("Helvetica", Font.ITALIC, cardWidth/5);		
		fm = this.getFontMetrics(defaultFont);
		StringWidth = fm.stringWidth(value)/2;
		FontHeight = defaultFont.getSize()*1/3;
		
		g.setColor(Color.white);
		g.setFont(defaultFont);
		g.drawString(value, 2*margin,5*margin);	
	}
	
	private void paintCardBack(Graphics2D g) {
		
		int cardWidth = CARDSIZE.width;
		int cardHeight = CARDSIZE.height;
		
		//Paints the external border of the card
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, cardWidth, cardHeight);
		
		//Paints the color of the card
		int margin = 5;
		g.setColor(Color.BLACK);
		g.fillRect(margin, margin, cardWidth-2*margin, cardHeight-2*margin);
		
		//Paints the oval format in the center of the card
		g.setColor(Color.RED);
		AffineTransform org = g.getTransform();
		g.rotate(45,cardWidth*3/4,cardHeight*3/4);		

		g.fillOval(0,cardHeight*1/4,cardWidth*3/5, cardHeight);
		g.setTransform(org);		
		
		//Value in the center		
		Font defaultFont = new Font("Helvetica", Font.BOLD, cardWidth/3);		
		FontMetrics fm = this.getFontMetrics(defaultFont);
		int StringWidth = fm.stringWidth("UNO")/2;
		int FontHeight = defaultFont.getSize()*1/3;
		
		g.setColor(Color.YELLOW);
		g.setFont(defaultFont);
		g.drawString("UNO", cardWidth/2-StringWidth, cardHeight/2+FontHeight);
	}
	/**
	 * My Mouse Listener 
	 */
	class MouseHandler extends MouseAdapter {
		
		public void mouseEntered(MouseEvent e){
			setBorder(focusedBorder);
		}
		
		public void mouseExited(MouseEvent e){
			setBorder(defaultBorder);
		}
	}
	
	public void setCardSize(Dimension newSize){
		this.setPreferredSize(newSize);
	}
	
	public void setColor(Color newColor) {
		this.cardColor = newColor;
	}

	public Color getColor() {
		return cardColor;
	}

	@Override
	public void setValue(String newValue) {
		this.value = newValue;		
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setType(int newType) {
		this.type = newType;
	}

	@Override
	public int getType() {
		return type;
	}
}
