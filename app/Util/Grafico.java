import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
 
public class Grafico extends JPanel {

	private static final long serialVersionUID = 1L;
	
	int[] x, y;
	int PAD;
	String labelx, labely;
	
    public Grafico(int[] x, int[] y, String labelx, String labely){
    	this.x = x;
    	this.y = y;
    	this.labelx = labelx;
    	this.labely = labely;
    	PAD = 20;
    }
    
    protected Graphics2D getGraficoPlotado(Graphics2D g2, int w, int h){
    	
    	g2.setPaint(Color.black);
    	 // Draw ordinate.
        g2.draw(new Line2D.Double(PAD, PAD, PAD, h-PAD));
        // Draw abcissa.
        g2.draw(new Line2D.Double(PAD, h-PAD, w-PAD, h-PAD));
        // Draw labels.
        Font font = g2.getFont();
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics("0", frc);
        float sh = lm.getAscent() + lm.getDescent();
        
        // Ordinate label.
        float sy = PAD + ((h - 2*PAD) - labely.length()*sh)/2 + lm.getAscent();
        for(int i = 0; i < labely.length(); i++) {
            String letter = String.valueOf(labely.charAt(i));
            float sw = (float)font.getStringBounds(letter, frc).getWidth();
            float sx = (PAD - sw)/2;
            g2.drawString(letter, sx, sy);
            sy += sh;
        }
        
        // Abcissa label.
        sy = h - PAD + (PAD - sh)/2 + lm.getAscent();
        float sw = (float)font.getStringBounds(labelx, frc).getWidth();
        float sx = (w - sw)/2;
        g2.drawString(labelx, sx, sy);
        
        
        // Draw lines.
        double padding = x.length*PAD > getMax(x) ? x.length*PAD : getMax(x);
        double xInc = (double)(w - padding)/(y.length-1);
        double scale = (double)(h - padding)/getMax(y);
        g2.setPaint(Color.green.darker());
        
        for(int i = 0; i < x.length-1; i++) {
            double x1 = PAD + x[i]*xInc;
            double y1 = h - PAD - scale*y[i];
            double x2 = PAD + x[i+1]*xInc;
            double y2 = h - PAD - scale*y[i+1];
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        }
        // Mark data points.
        g2.setPaint(Color.red);
        for(int i = 0; i < x.length; i++) {
            double x1 = PAD + x[i]*xInc;
            double y1 = h - PAD - scale*y[i];
            g2.fill(new Ellipse2D.Double(x1, y1, 4, 4));
        }
        
        return g2;
    }
 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        
       g2 = getGraficoPlotado(g2, getWidth(), getHeight());
    }
    
    public void save(String output_image){
    	BufferedImage bImg = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
        Graphics cg = bImg.getGraphics();
        cg.setColor(Color.white);  
        cg.fillRect(0, 0, 400, 400); 
        
        cg = (Graphics)getGraficoPlotado((Graphics2D)cg, 400, 400);
        
        try {
            if (ImageIO.write(bImg, "png", new File(output_image)))
	            {
	                System.out.println("-- saved");
	            }
	    } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	    }
    }
 
    private int getMax(int[] data) {
        int max = -Integer.MAX_VALUE;
        for(int i = 0; i < data.length; i++) {
            if(data[i] > max)
                max = data[i];
        }
        return max;
    }
    
    public static void plotaGrafico(int[] x, int[] y, String labelx, String labely) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new Grafico(x, y, labelx, labely));
        f.setSize(400,400);
    
        f.setLocation(200,200);
        f.setVisible(true);
    }
    
    public static void salvaGrafico(int[] x, int[] y, String labelx, String labely, String nome) {
        Grafico g = new Grafico(x, y, labelx, labely);
        g.save(nome);
    }
    

}