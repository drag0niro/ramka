package ramka;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * @version 1.02
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
//        Container container = this.getContentPane(); //Pobieranie szybki i przypisanie jej do kontenera
//        container.setLayout(null);
//        container.add(buttonPageEnd);
//        buttonPageEnd.setSize(buttonPageEnd.getPreferredSize());
//        buttonPageEnd.setLocation(200, 200);
        
        /**
         * @since 1.02
         */
        this.getContentPane().add(panel); 
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        
        layout.setHorizontalGroup( //Grupa pozioma
                layout.createSequentialGroup()
//                .addComponent(button1, GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE) // 1. Minimalna wielkośc, 2. Preferowana wielkość, 3. Maksymalna wielkość
//                .addGroup(
//                    layout.createParallelGroup().
//                            addComponent(button2, GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE).
//                            addComponent(button4, GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
//                 )
//                .addComponent(button3, GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addContainerGap(10, Short.MAX_VALUE) //Przerwa, żeby umieścić przycisk na dole strony
                .addComponent(buttonPageEnd, GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
        );
        
        layout.setVerticalGroup( //Grupa pionowa
                layout.createSequentialGroup()
//                .addGroup(
//                layout.createParallelGroup()
//                        .addComponent(button1, GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
//                        .addComponent(button2, GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
//                        .addComponent(button3, GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
//                )
//                .addComponent(button4, GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addContainerGap(10, Short.MAX_VALUE) //Przerwa, żeby umieścić przycisk na dole strony
                .addComponent(buttonPageEnd, GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
        );
       
        layout.setAutoCreateGaps(true); //Dodawanie odstępów między buttonami
        layout.setAutoCreateContainerGaps(true); //Dodawanie odstępów między kontenerami
        

        buttonPageEnd.addActionListener(new colorListener(Color.BLACK));

    }
    
    private class colorListener implements ActionListener
    {
        public colorListener(Color color)
        {
            this.color = color;
        }
        
        @Override
        public void actionPerformed(ActionEvent event)
        {
            panel.setBackground(color);

        }
        
        Color color;
    }
    
    JPanel panel = new JPanel();
    JButton buttonPageEnd = new JButton("Zmiana tła na kolor czarny"); //Zainicjowanie przycisku z napisem
//    JButton button1 = new JButton("B1"); //Zainicjowanie przycisku z napisem
//    JButton button2 = new JButton("B2"); //Zainicjowanie przycisku z napisem
//    JButton button3 = new JButton("B3"); //Zainicjowanie przycisku z napisem
//    JButton button4 = new JButton("B4"); //Zainicjowanie przycisku z napisem
    
    
    public static void main(String[] args) 
    {
        new Ramka().setVisible(true);
        
    }
    
}
