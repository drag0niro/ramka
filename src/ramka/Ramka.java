package ramka;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.GregorianCalendar;


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
        
        /**
         * @since 1.02
         */
        this.getContentPane().add(panel); 
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        
        layout.setVerticalGroup( //Grupa pozioma
                layout.createSequentialGroup()
                .addComponent(time)
                .addContainerGap(10, Short.MAX_VALUE) //Przerwa, żeby umieścić przycisk na dole strony
                .addComponent(buttonPageEnd, GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)

        );
        
        layout.setHorizontalGroup( //Grupa pionowa
                layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE) //Przerwa, żeby umieścić przycisk na dole strony
                .addGroup(layout.createParallelGroup()
                .addComponent(time)
                .addComponent(buttonPageEnd, GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE))
                
                


        );
       
        layout.setAutoCreateGaps(true); //Dodawanie odstępów między buttonami
        layout.setAutoCreateContainerGaps(true); //Dodawanie odstępów między kontenerami
        

        buttonPageEnd.addActionListener(new colorListener(Color.RED));
        panel.add(time);
        
        ActionListener stoper = new Clock();
        Timer clock = new Timer(1000, stoper);
        clock.start();

    }
    
    private class Clock implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            time.setText(getTime());
        }
    }
    
    public String getTime()
    {
        GregorianCalendar calendar = new GregorianCalendar();
        String day = "" + calendar.get(GregorianCalendar.DAY_OF_WEEK);
        String h = "" + calendar.get(GregorianCalendar.HOUR_OF_DAY);
        String min = "" + calendar.get(GregorianCalendar.MINUTE);
        String sec = "" + calendar.get(GregorianCalendar.SECOND);
        
        if (Integer.parseInt(day) == 2)
            day = "Monday";
        else if (Integer.parseInt(day) == 3)
            day = "Tuesday";
        else if (Integer.parseInt(day) == 4)
            day = "Wednesday";
        else if (Integer.parseInt(day) == 5)
            day = "Thursday";
        else if (Integer.parseInt(day) == 6)
            day = "Friday";
        else if (Integer.parseInt(day) == 7)
            day = "Saturday";
        else if (Integer.parseInt(day) == 1)
            day = "Sunday";
        
        
        if (Integer.parseInt(h) < 10)
            h = "0" + h;
        if (Integer.parseInt(min) < 10)
            min = "0" + min;
        if (Integer.parseInt(sec) < 10)
            sec = "0" + sec;
        
        return (day + ", " + h + " : " + min + " : " + sec);
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
    JButton buttonPageEnd = new JButton("RED Background"); //Zainicjowanie przycisku z napisem
    JLabel time = new JLabel(getTime());
    
    
    public static void main(String[] args) 
    {
        new Ramka().setVisible(true);
        
    }
    
}
