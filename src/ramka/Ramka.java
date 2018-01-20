package ramka;
import javax.swing.*;
import java.awt.*;


/**
 * @version 1.0
 * @author Damian
 */
public class Ramka extends JFrame
{
    public Ramka()
    {
        this.setVisible(true);        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Solid Deraking Team");
        this.setSize(1280, 720);

        this.setIconImage(Toolkit.getDefaultToolkit().getImage(Ramka.class.getResource("logo.png")));
//        this.pack();
        this.setLocationRelativeTo(null); //Centruje ramkę, musi być zawsze po pack() i setSize()
    }
    
    
    public static void main(String[] args) 
    {
        new Ramka();
        
    }
    
}
