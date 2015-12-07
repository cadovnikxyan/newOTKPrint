package newOTKPrint;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageView extends JPanel{
	private static final long serialVersionUID = 1L;
    private BufferedImage image;
    private JPanel canvas;
    private static float defaultScale=0.2f;

    public ImageView(String filepath) {
        try {
            this.image = ImageIO.read(new File(filepath));
        }catch(IOException ex) {
            Logger.getLogger(ImageView.class.getName()).log(Level.SEVERE, null, ex);
        }

               addMouseWheelListener(new MouseAdapter() {
            	   
            	@Override
            	public void mouseWheelMoved(MouseWheelEvent e){
            		double delta=0.05f*e.getWheelRotation();
            		defaultScale+=delta;
            		revalidate();
            		repaint();
            	}
               });
           
            	this.canvas=new JPanel(){
					private static final long serialVersionUID = 1L;

				@Override
            	protected void paintComponent(Graphics g){
            		super.paintComponent(g);
//            		g.drawImage(image, 0, 0,image.getWidth()/4 ,image.getHeight()/4, null);
            		Graphics2D g2d=(Graphics2D) g.create();
            		AffineTransform at = new AffineTransform();
            		at.scale(defaultScale, defaultScale);
            		 g2d.drawImage(image,at,this);
            		 g2d.dispose();
            	}
            	};
         
        
       
        canvas.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);
        canvas.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
    	
    }
 	@Override
	public Dimension getPreferredSize(){
		Dimension size= new Dimension(200,200);
		if(image!=null){
			size.width=Math.round(image.getWidth()*defaultScale);
			size.height=Math.round(image.getHeight()*defaultScale);
		}
		return size;
	}
	    public int getImageWidth(){
			return image.getWidth();
	    }
	    public int getImageHeight(){
	    	return image.getHeight();
	    }
	    
	   
	    
    }
