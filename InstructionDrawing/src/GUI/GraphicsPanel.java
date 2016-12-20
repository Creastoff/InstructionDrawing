package GUI;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
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
	    Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
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

	//Draws spirals at the current coordinate - sourced from:
	//http://stackoverflow.com/questions/29638733/java-draw-a-circular-spiral-using-drawarc
	public void drawSpiral(int paramWidth, int paramHeight, int paramGap, int paramSize) {
		Graphics g = getGraphic();
		g.setColor(GraphicsPanel.color);
		
		//Draws The Spiral
        int width = paramWidth;
        int height = paramHeight;
        int startAngle = 0;
        int arcAngle = 180;
        int depth = paramGap;
        
        for (int i = 0; i < paramSize; i++) {
            if (i % 2 == 0) {
            	GraphicsPanel.y = GraphicsPanel.y - depth;
                width = width + 2 * depth;
                height = height + 2 * depth;
                g.drawArc(GraphicsPanel.x, GraphicsPanel.y, width, height, startAngle, -arcAngle);
            } else {
            	GraphicsPanel.x = GraphicsPanel.x - 2 * depth;
                GraphicsPanel.y = GraphicsPanel.y - depth;
                width = width + 2 * depth;
                height = height + 2 * depth;
                g.drawArc(GraphicsPanel.x, GraphicsPanel.y, width, height, startAngle, arcAngle);
            }
        }
	}
	
	public void drawOval(int r1, int r2) {
		Graphics g = getGraphic();
		g.setColor(GraphicsPanel.color);
		g.drawOval(GraphicsPanel.x, GraphicsPanel.y, r1, r2);
	}
	
	public void drawSolidOval(int r1, int r2) {
		Graphics g = getGraphic();
		g.setColor(GraphicsPanel.color);
		g.fillOval(GraphicsPanel.x, GraphicsPanel.y, r1, r2);
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
	
	public void drawIsosceles(int width, int height) {
		Graphics g = getGraphic();
		g.setColor(GraphicsPanel.color);
		int[] Xcoordinates = {GraphicsPanel.x, GraphicsPanel.x + width, GraphicsPanel.x};
		int[] Ycoordinates = {GraphicsPanel.y, GraphicsPanel.y, GraphicsPanel.y - height};
		Polygon polygon = new Polygon(Xcoordinates, Ycoordinates, 3);
		g.drawPolygon(polygon);
	}
	
	public void drawSolidIsosceles(int width, int height) {
		Graphics g = getGraphic();
		g.setColor(GraphicsPanel.color);
		int[] Xcoordinates = {GraphicsPanel.x, GraphicsPanel.x + width, GraphicsPanel.x};
		int[] Ycoordinates = {GraphicsPanel.y, GraphicsPanel.y, GraphicsPanel.y - height};
		Polygon polygon = new Polygon(Xcoordinates, Ycoordinates, 3);
		g.fillPolygon(polygon);
	}
	
	public void drawTetrisL(int side, int width, int height) {
		Graphics g = getGraphic();
		g.setColor(GraphicsPanel.color);
		int x = GraphicsPanel.x, y = GraphicsPanel.y;
		int[] Xcoordinates = new int[6];
		int[] Ycoordinates = new int[6];
		
		switch(side) {
			//__
			//| |
			//| |_
			//|___|
			case 0:
				//X Coordinates
				Xcoordinates[0] = x;
				Xcoordinates[1] = x + width;
				Xcoordinates[2] = x + width;
				Xcoordinates[3] = x + width / 2;
				Xcoordinates[4] = x + width / 2;
				Xcoordinates[5] = x;
				//Y Coordinates
				Ycoordinates[0] = y;
				Ycoordinates[1] = y;
				Ycoordinates[2] = y - height / 3;
				Ycoordinates[3] = y - height / 3;
				Ycoordinates[4] = y - height;
				Ycoordinates[5] = y - height;
				break;
			//______
			//|  ___|
			//|_|
			case 1:
				//X Coordinates
				Xcoordinates[0] = x;
				Xcoordinates[1] = x;
				Xcoordinates[2] = x + height / 3;
				Xcoordinates[3] = x + height / 3;
				Xcoordinates[4] = x + height;
				Xcoordinates[5] = x + height;
				//Y Coordinates
				Ycoordinates[0] = y;
				Ycoordinates[1] = y + width;
				Ycoordinates[2] = y + width;
				Ycoordinates[3] = y + width / 2;
				Ycoordinates[4] = y + width / 2;
				Ycoordinates[5] = y;
				break;
			//____
			//|_  |
			//	| |
			//	|_|
			case 2:
				//X Coordinates
				Xcoordinates[0] = x;
				Xcoordinates[1] = x - width;
				Xcoordinates[2] = x - width;
				Xcoordinates[3] = x - width / 2;
				Xcoordinates[4] = x - width / 2;
				Xcoordinates[5] = x;
				//Y Coordinates
				Ycoordinates[0] = y;
				Ycoordinates[1] = y;
				Ycoordinates[2] = y + height / 3;
				Ycoordinates[3] = y + height / 3;
				Ycoordinates[4] = y + height;
				Ycoordinates[5] = y + height;
				break;
			// _____
			//|___  |
			//    |_|
			case 3:
				//X Coordinates
				Xcoordinates[0] = x;
				Xcoordinates[1] = x - height;
				Xcoordinates[2] = x - height;
				Xcoordinates[3] = x - height / 3;
				Xcoordinates[4] = x - height / 3;
				Xcoordinates[5] = x;
				//Y Coordinates
				Ycoordinates[0] = y;
				Ycoordinates[1] = y;
				Ycoordinates[2] = y + width / 2;
				Ycoordinates[3] = y + width / 2;
				Ycoordinates[4] = y + width;
				Ycoordinates[5] = y + width;
				break;
		}
		
		Polygon polygon = new Polygon(Xcoordinates, Ycoordinates, 6);
		g.drawPolygon(polygon);
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
		//int resultantWidth = x + width;
		//int resultantHeight = y + height;
		
		if(width < 0 || height < 0)
			message = "Width & Height must be larger than 0";
		/*
		else if((resultantWidth > horizontalSize) && (resultantHeight > verticalSize)) {
			message = "The width(" + width + ") and x coordinate(" + GraphicsPanel.x + ") must result (" + resultantWidth + ") in a number less than " + horizontalSize + ".\n"
					+ "The height(" + height + ") and y coordinate(" + GraphicsPanel.y + ") must result (" + resultantHeight + ") in a number less than " + verticalSize + ".";
		} else if(resultantWidth > horizontalSize) {
			message = "The width(" + width + ") and x coordinate(" + GraphicsPanel.x + ") must result (" + resultantWidth + ") in a number less than " + horizontalSize + ".\n";
		} else if(resultantHeight > verticalSize) {
			System.out.println(resultantHeight);
			message = "The height(" + height + ") and y coordinate(" + y + ") must result (" + resultantHeight + ") in a number less than " + verticalSize + ".";
		}
		*/
		return message;
	}


	public static Dimension getGraphicsSize() {
		return new Dimension(horizontalSize, verticalSize);
	}
}