/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaBean;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.net.URI;
import javax.swing.JButton;

/**
 *
 * @author Yamelis Calderon
 */
public class BotonInternet extends JButton implements Serializable 
{
    private String link = "";
    private boolean dentro;
    private boolean round; 

    public BotonInternet()
    {
        super(); 
       round = true; 
       setContentAreaFilled(false); 
        

         addMouseListener(new MouseAdapter() 
         {

            @Override
            public void mousePressed(MouseEvent me) 
            {
                abrirLink();
            }

            @Override
            public void mouseEntered(MouseEvent me) 
            {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                dentro = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) 
            {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                dentro = false;
                repaint();
            }

        });
    }
private int f, f1; //Esto es para determinar la anchura y altura
    private void abrirLink() 
    {
        try {
            Desktop.getDesktop().browse(new URI(link));
        } catch (Exception e) {
        }
    }

    @Override
    public void paint(Graphics g) 
    {
        Graphics2D gd = (Graphics2D)g;
        
        if(dentro)
        {
            gd.rotate(Math.toRadians(30), getWidth()/2, getHeight()/2); //Aqui es para rotar los graficos con respecto al centro del componente
        }
        
        //super.paint(gd);
       
         if (round) { 
            g.fillOval(0, 0, getSize().width - 1, getSize().height - 1); 
        } else { 
            g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, f, f1); 
        } 
        
        super.paintComponent(g); 
    }

    public String getLink() 
    {
        return link;
    }

    public void setLink(String link) 
    {
        this.link = link;
    }
    
    
    
}
