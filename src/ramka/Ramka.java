package ramka;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import javax.swing.filechooser.FileSystemView;


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

                .addContainerGap(10, Short.MAX_VALUE) //Przerwa, żeby umieścić przycisk na dole strony
                .addGroup(layout.createParallelGroup()
                .addComponent(szukajFolderow, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addComponent(szukajWszystkiego, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE))

                .addComponent(buttonPageEnd, GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)


        );
        
        layout.setHorizontalGroup( //Grupa pionowa
                layout.createSequentialGroup()
                .addComponent(suwaki, 900, 900, 900)
                .addContainerGap(10, Short.MAX_VALUE) //Przerwa, żeby umieścić przycisk na dole strony
                
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(time, GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addComponent(buttonPageEnd, GroupLayout.DEFAULT_SIZE , GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGroup(layout.createSequentialGroup()
                .addComponent(szukajFolderow, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addComponent(szukajWszystkiego, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE))
                )

        );
       
        layout.setAutoCreateGaps(true); //Dodawanie odstępów między buttonami
        layout.setAutoCreateContainerGaps(true); //Dodawanie odstępów między kontenerami
        

        buttonPageEnd.addActionListener(new zapisDoPlikuListenter());
        buttonPageEnd.setEnabled(false);
        obszarTekstowy.setEditable(false);
        panel.add(time, BorderLayout.PAGE_END);
        szukajFolderow.addActionListener(new szukajFolderowListener());
        szukajWszystkiego.addActionListener(new szukajWszystkiegoListener());
        
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
    
    
    private class szukajFolderowListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {  
            int opcja = JOptionPane.showConfirmDialog(rootPane, "Czy na pewno chcesz wyszukać wszystkie foldery na dysku " + dysk + "?", "Uwaga", JOptionPane.YES_NO_OPTION);
            if (opcja == 0)
            {
                folderyNaDysku(new File(dysk));
                szukajFolderow.transferFocusBackward(); //Bez tego nie działa zaznaczenie, focus zostaje dalej na przycisku
                System.out.println(obszarTekstowy.getSelectionStart());
                buttonPageEnd.setEnabled(true);
            }
        }

    }
    
     private class szukajWszystkiegoListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {              
            int opcja = JOptionPane.showConfirmDialog(rootPane, "Czy na pewno chcesz wyszukać wszystkie pliki i foldery na dysku " + dysk + "?", "Uwaga", JOptionPane.YES_NO_OPTION);
            if (opcja == 0)
            {
                plikiIFolderyNaDysku(new File(dysk));
                szukajFolderow.transferFocusBackward(); //Bez tego nie działa zaznaczenie, focus zostaje dalej na przycisku
                System.out.println(obszarTekstowy.getSelectionStart());
                buttonPageEnd.setEnabled(true);
            }
        }
        
    }
        
    private class zapisDoPlikuListenter implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                FileSystemView filesys = FileSystemView.getFileSystemView();
                File[] roots = filesys.getRoots();
                File pulpit = filesys.getHomeDirectory();
                PrintWriter zapis = new PrintWriter(new FileWriter(new File(pulpit,"Pliki z dysku " + nazwaDysku + ".txt")));
                zapis.print(getTime());
                zapis.println();
                zapis.println();
                zapis.print(obszarTekstowy.getText());
                zapis.close();
                JOptionPane.showMessageDialog(rootPane, "Pomyślnie wykonano zapis na " + pulpit);
            }
            catch (IOException ex)
            {
                System.out.println(ex.getMessage()); 
            }
               
        }
        
    }
    
    public void folderyNaDysku(File nazwaSciezki)
    {
        int liczba = 0;
        String[] nazwyFolderow = nazwaSciezki.list();
        obszarTekstowy.setText(null);
        obszarTekstowy.append("Foldery na dysku " + dysk);
        obszarTekstowy.append(System.getProperty("line.separator"));
        obszarTekstowy.append(System.getProperty("line.separator"));
        
        for (int i = 0; i < nazwyFolderow.length; ++i )
        {
            File p = new File(nazwaSciezki.getPath(), nazwyFolderow[i]);
                    if (!p.isHidden())
                    {
                        String sciezka = p+" "+System.getProperty("line.separator");
                        sciezka = sciezka.substring(3);
                        obszarTekstowy.append(sciezka); 
                    }
        liczba++;
        }
        obszarTekstowy.append(System.getProperty("line.separator"));
        obszarTekstowy.append("Liczba folderów na dysku " + nazwaDysku + ": " + liczba);
            
    }
    
        public void plikiIFolderyNaDysku(File nazwaSciezki)
    {
        int liczba = 0;
        String[] nazwyFolderow = nazwaSciezki.list();
        obszarTekstowy.setText(null);
        obszarTekstowy.append("Pliki i foldery na dysku " + dysk);
        obszarTekstowy.append(System.getProperty("line.separator"));
        obszarTekstowy.append(System.getProperty("line.separator"));
        
        for (int i = 0; i < nazwyFolderow.length; ++i )
        {
            File p = new File(nazwaSciezki.getPath(), nazwyFolderow[i]);
                    if (!p.isHidden())
                    {
                        String sciezka = p+" "+System.getProperty("line.separator");
                        sciezka = sciezka.substring(3);
                        obszarTekstowy.append(sciezka); 
                    }
        liczba++;           
        }
        obszarTekstowy.append(System.getProperty("line.separator"));
        obszarTekstowy.append("Liczba folderów i plików na dysku " + nazwaDysku + ": " + liczba);    
    }
    
    private JPanel panel = new JPanel();
    private JButton buttonPageEnd = new JButton("Zapisz listę na pulpicie"); //Zainicjowanie przycisku z napisem
    private JButton szukajFolderow = new JButton("Szukaj folderów");
    private JButton szukajWszystkiego = new JButton("Szukaj wszystkiego");
    private JLabel time = new JLabel(getTime());
    private JTextArea obszarTekstowy = new JTextArea();
    private JScrollPane suwaki = new JScrollPane(obszarTekstowy);
    private JFileChooser wyborSciezki = new JFileChooser();
    private String dysk = File.listRoots()[2].getAbsolutePath();
    private String nazwaDysku = dysk.substring(0, 1);

    
    public static void main(String[] args) 
    {
        new Ramka().setVisible(true);
        
    }
    
}
