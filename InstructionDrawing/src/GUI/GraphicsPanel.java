package GUI;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class GraphicsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final static Color BACKGROUND_COL = Color.BLACK;
	private static Color color = Color.white;
	private static int x = 0, y = 0;
	private static int horizontalSize = 800, verticalSize = 800;
	private static BufferedImage image;

	public GraphicsPanel() {
		setPreferredSize(new Dimension(horizontalSize, verticalSize));
		image = new BufferedImage(horizontalSize, verticalSize, BufferedImage.TYPE_INT_RGB);
		
		// Set max size of the panel, so that is matches the max size of the image.
		setMaximumSize(new Dimension(image.getWidth(), image.getHeight()));		
		clear();
	}
	
	public void setPos(int x, int y) {
		GraphicsPanel.x = x;
		GraphicsPanel.y = y;
	}
	
	public void drawLine(int x, int y) {
		Graphics g = getGraphic();
		g.setColor(GraphicsPanel.color);
		g.drawLine(GraphicsPanel.x, GraphicsPanel.y, x, y);
		this.setPos(x, y);
	}
	
	public void drawDashedLine(int x, int y) {
		Graphics2D g2d = (Graphics2D) getGraphic().create();
		g2d.setColor(GraphicsPanel.color);
		
		//set the stroke of the copy, not the original 
	    Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
	    g2d.setStroke(dashed);
	       
		g2d.drawLine(GraphicsPanel.x, GraphicsPanel.y, x, y);
		this.setPos(x, y);
	}

	public void drawRectangle(int width, int height) {
		Graphics g = getGraphic();
		g.setColor(GraphicsPanel.color);
		g.drawRect(GraphicsPanel.x, GraphicsPanel.y, width, height);
	}
	
	public void drawSolidRectangle(int width, int height) {
		Graphics g = getGraphic();
		g.setColor(GraphicsPanel.color);
		g.fillRect(GraphicsPanel.x, GraphicsPanel.y, width, height);
	}
	
	public void drawCircle(int radius) {
		Graphics g = getGraphic();
		g.setColor(GraphicsPanel.color);
		g.drawOval(GraphicsPanel.x, GraphicsPanel.y, radius, radius);
	}
	
	public void drawSolidCircle(int radius) {
		Graphics g = getGraphic();
		g.setColor(GraphicsPanel.color);
		g.drawOval(GraphicsPanel.x, GraphicsPanel.y, radius, radius);
		g.fillOval(GraphicsPanel.x, GraphicsPanel.y, radius, radius);
	}
	
	public void drawText(String str) {
		Graphics g = getGraphic();
		g.setColor(GraphicsPanel.color);
		g.drawString(str, GraphicsPanel.x, GraphicsPanel.y);
	}

	public void setColor(Color color) {
		GraphicsPanel.color = color;
	}
	
	public void clear() {
		Graphics g = image.getGraphics();
		this.setPos(0, 0);
		g.setColor(BACKGROUND_COL);
		g.fillRect(0, 0, image.getWidth(),  image.getHeight());
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		// render the image on the panel.
		g.drawImage(image, 0, 0, null);
	}
	
	private Graphics getGraphic() {
		return image.getGraphics();
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public int getXCoordinate() {
		return GraphicsPanel.x;
	}
	
	public int getYCoordinate() {
		return GraphicsPanel.y;
	}
	
	//Check if the coordinates leave the image
	public String checkCoordinates(int x, int y) {
		String message = "OKAY";
		
		if((x < 0 || x > horizontalSize) && (y < 0 || y > verticalSize)) {
			message = "The x coordinate (" + x + ") must be larger than 0 and less than " + horizontalSize + ".\n"
					+ "The y coordinate (" + y + ") must be larger than 0 and smaller than " + verticalSize + ".";
		} else if(x < 0 || x > horizontalSize) {
			message = "The x coordinate (" + x + ") must be larger than 0 and less than " + horizontalSize + ".\n";
		} else if(y < 0 || y > verticalSize) {
			message = "The y coordinate (" + y + ") must be larger than 0 and smaller than " + verticalSize + ".";
		}
		
		return message;
	}
	
	//Check if a shape is too large for the image
	public String checkShapeSize(int width, int height) {
		String message = "OKAY";

		if(width < 0 || height < 0)
			message = "Width & Height must be larger than 0";

		return message;
	}
	
	public static Dimension getGraphicsSize() {
		return new Dimension(horizontalSize, verticalSize);
	}
}