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
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Solid Deraking Team");


        this.setIconImage(Toolkit.getDefaultToolkit().getImage(Ramka.class.getResource("logo.png")));
        initComponents(); //Inicjalizuje klasę, w której dodaje się komponenty
        this.pack(); //Ustawia wielkość okna dopasowane do komponentów
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null); //Centruje ramkę, musi być zawsze po pack() i setSize()
    }
 /**
  * @since 1.01
  * 
  */   
    
    public void initComponents()
    {
        buttonPageEnd = new JButton("Accept");
        Container container = this.getContentPane(); //Pobieranie szybki i przypisanie jej do kontenera
        container.setLayout(null);
        container.add(buttonPageEnd);
        buttonPageEnd.setSize(buttonPageEnd.getPreferredSize());
        buttonPageEnd.setLocation(200, 200);
    }
 
    JButton buttonPageEnd;
    
    public static void main(String[] args) 
    {
        new Ramka().setVisible(true);
        
    }
    
}
