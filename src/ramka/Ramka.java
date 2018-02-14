package ramka;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
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
        this.getContentPane().add(suwaki);
        this.getContentPane().add(panel); 
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        
        layout.setVerticalGroup( //Grupa pozioma
                layout.createSequentialGroup()
                .addComponent(time, GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addComponent(suwaki, 550, 550, 550)
            //    .addComponent(wyborSciezki, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addContainerGap(10, Short.MAX_VALUE) //Przerwa, żeby umieścić przycisk na dole strony
                .addComponent(zaznaczenie, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addComponent(buttonPageEnd, GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)


        );
        
        layout.setHorizontalGroup( //Grupa pionowa
                layout.createSequentialGroup()
                .addComponent(suwaki, 900, 900, 900)
                .addContainerGap(10, Short.MAX_VALUE) //Przerwa, żeby umieścić przycisk na dole strony
              //  .addComponent(wyborSciezki, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(time, GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addComponent(buttonPageEnd, GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addComponent(zaznaczenie, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE))

        );
       
        layout.setAutoCreateGaps(true); //Dodawanie odstępów między buttonami
        layout.setAutoCreateContainerGaps(true); //Dodawanie odstępów między kontenerami
        

        buttonPageEnd.addActionListener(new colorListener(Color.RED));
        panel.add(time, BorderLayout.PAGE_END);
        zaznaczenie.addActionListener(new zaznaczenieListener());
        
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
    
    
    private class zaznaczenieListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {  
//            obszarTekstowy.insert("No siemanko! ", 0);
//            obszarTekstowy.replaceRange("Siema", 0, 20);
            String dysk = File.listRoots()[3].getAbsolutePath(); 
            int opcja = JOptionPane.showConfirmDialog(rootPane, "Czy na pewno chcesz wyszukać wszystkie foldery na dysku " + dysk + "?", "Uwaga", JOptionPane.YES_NO_OPTION);
            if (opcja == 0)
            {
                folderyNaDysku(new File(dysk));
//                obszarTekstowy.select(0, obszarTekstowy.getText().length());   //To samo co .selectAll()
                zaznaczenie.transferFocusBackward(); //Bez tego nie działa zaznaczenie, focus zostaje dalej na przycisku
                System.out.println(obszarTekstowy.getSelectionStart());
            }
        }

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
    
    public void folderyNaDysku(File nazwaSciezki)
    {
        String[] nazwyFolderow = nazwaSciezki.list();
        
        for (int i = 0; i < nazwyFolderow.length; ++i )
        {
            File p = new File(nazwaSciezki.getPath(), nazwyFolderow[i]);
                    if (!p.isHidden())
                    {
                        String sciezka = p+" "+System.getProperty("line.separator");
                        obszarTekstowy.append(sciezka); 
                    }

        }
            
    }
    
    private JPanel panel = new JPanel();
    private JButton buttonPageEnd = new JButton("Zapisz do pliku"); //Zainicjowanie przycisku z napisem
    private JButton zaznaczenie = new JButton("Szukaj folderów");
    private JLabel time = new JLabel(getTime());
    private JTextArea obszarTekstowy = new JTextArea();
    private JScrollPane suwaki = new JScrollPane(obszarTekstowy);
    private JFileChooser wyborSciezki = new JFileChooser();
    
    
    public static void main(String[] args) 
    {
        new Ramka().setVisible(true);
        
    }
    
}
