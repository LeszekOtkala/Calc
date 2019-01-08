/*
    *Program kalkulator, wykonujący podstawowe działania
    *
    *Wprowadzanie danych: użyj klawiszy strzałek lub klawisza TAB żeby podświetlić właściwy przycisk, 
    *Aby wykonać operację danego przycisku należy wcisnąć klawisz enter lub spację.
 */
package Calc;


import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;


import java.io.IOException;

import java.util.regex.Pattern;
/**
 * 
 * @author Leszek Otkała
 * @version 0.1
 */
public class Kalkulator {
    //metody wykonujac poszczegolne obliczenia
    public static String dodaj(String s1, String s2) {
        double a=Double.valueOf(s1);
        double b=Double.valueOf(s2);
        double c=a+b;
        String d = String.valueOf(c);
        return d;
    }
    public static String odejmij(String s1, String s2) {
        double a=Double.valueOf(s1);
        double b=Double.valueOf(s2);
        double c=a-b;
        String d = String.valueOf(c);
        return d; 
     }
    public static String pomnoz(String s1, String s2) {
        double a=Double.valueOf(s1);
        double b=Double.valueOf(s2);
        double c=a*b;
        String d = String.valueOf(c);
        return d; 
     }
     public static String podziel(String s1, String s2) {
        double a=Double.valueOf(s1);
        double b=Double.valueOf(s2);
        if(b==0) return "ERROR";
        else{
        double c=a/b;
        String d = String.valueOf(c);
        return d;
        }
     }
    public static void main(String[] args) throws IOException {
        String s=new String();
        
        //Ustawienie terminala i ekranu
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();
        //stworzenie głównego panelu do urzymywania komponentów
        Panel mainPanel=new Panel();   
        mainPanel.setLayoutManager(new GridLayout(1));
        
        
        //panel z polami do wprowadzania danych
        Panel panel = new Panel();
        panel.setPreferredSize(new TerminalSize(40,8));
        panel.setLayoutManager(new GridLayout(4));
        //panel2 do wyświetlenia rezultatów
        Panel panel2 = new Panel();
        panel2.setLayoutManager(new GridLayout(2));
        panel2.setPreferredSize(new TerminalSize(40,6));
        Panel wyswietlacz=new Panel();
        wyswietlacz.setPreferredSize(new TerminalSize(20,1));
        mainPanel.addComponent(panel2.withBorder(Borders.singleLine("Operacje")));
        mainPanel.addComponent(panel.withBorder(Borders.doubleLine()));
        
        //komponenty do wyświetlania wyników oeracji
        final TextBox text1 = new TextBox("0").setReadOnly(true).setPreferredSize(new TerminalSize(20,1)).addTo(panel); 
        final Label liczba2=new Label("----");
        final Label operacja=new Label("--");
        final Label liczba3=new Label("----");
        //rozmieszczenie komponentów w panelu 2
        panel2.addComponent(liczba2);
        panel2.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel2.addComponent(operacja);
        panel2.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel2.addComponent(liczba3);
        panel2.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel2.addComponent(new EmptySpace(new TerminalSize(20,0)));
        panel2.addComponent(wyswietlacz.withBorder(Borders.singleLine("Wynik")));
        wyswietlacz.addComponent(text1);
        
        //komponenty panelu do wprowadzania danych
        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        panel.addComponent(new Label("Liczba:"));
        final TextBox liczba1 = new TextBox("0").setReadOnly(true).setValidationPattern(Pattern.compile("[0-9]*.[0-9]*")).addTo(panel);
        liczba1.setPreferredSize(new TerminalSize(8,1));
        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
                
        new Button("+", new Runnable() {
		@Override
		public void run() {
                 //dzialania po wcisnieciu przycisku  
                operacja.setText("+");
                String s1=text1.getText();
                String s2=liczba1.getText();
                String s3=liczba2.getText();
                if(s3.equals("----")){
                liczba2.setText(s2); 
                }
                else if(s1.equals("0")){
                liczba3.setText(s2);
                text1.setText(dodaj(s3, s2));
                }
                else{
                liczba2.setText(s1);
                liczba3.setText(s2);
                text1.setText(dodaj(s1,s2));
                }
                liczba1.setText("0");
                
                }
	}).addTo(panel);
        new Button("-", new Runnable() {
		@Override
		public void run() {
                operacja.setText("-"); 
                String s1=text1.getText();
                String s2=liczba1.getText();
                String s3=liczba2.getText();
                if(s3.equals("----")){
                liczba2.setText(s2); 
                }
                else if(s1.equals("0")){
                liczba3.setText(s2);
                text1.setText(odejmij(s3, s2));
                }
                else{
                liczba2.setText(s1);
                liczba3.setText(s2);
                text1.setText(odejmij(s1,s2));
                }
                liczba1.setText("0");
                
                }
	}).addTo(panel);
        new Button("*", new Runnable() {
		@Override
		public void run() {
                operacja.setText("*"); 
                String s1=text1.getText();
                String s2=liczba1.getText();
                String s3=liczba2.getText();
                if(s3.equals("----")){
                liczba2.setText(s2); 
                }
                else if(s1.equals("0")){
                liczba3.setText(s2);
                text1.setText(pomnoz(s3, s2));
                }
                else{
                liczba2.setText(s1);
                liczba3.setText(s2);
                text1.setText(pomnoz(s1,s2));
                }
                liczba1.setText("0");
                
                }
	}).addTo(panel);
        new Button("/", new Runnable() {
		@Override
		public void run() {
                operacja.setText("/");
                String s1=text1.getText();
                String s2=liczba1.getText();
                String s3=liczba2.getText();
                if((s2.compareTo("0"))!=0){
                if(s3.equals("----")){
                liczba2.setText(s2); 
                }
                else if(s1.equals("0")){
                liczba3.setText(s2);
                text1.setText(podziel(s3, s2));
                }
                else{
                liczba2.setText(s1);
                liczba3.setText(s2);
                text1.setText(podziel(s1,s2));
                }
                }
                liczba1.setText("0");
                
                }
	}).addTo(panel);
                 
        panel.addComponent(new EmptySpace().setLayoutData(GridLayout.createHorizontallyFilledLayoutData(4)));
       new Button("7", new Runnable() {
		@Override
		public void run() {
                String s3=liczba1.getText();
                if((s3.compareTo("0"))!=0)
                liczba1.setText(s3+"7");
                else  
                liczba1.setText("7");
                }
	}).addTo (panel);
        new Button("8", new Runnable() {
		@Override
		public void run() {
                String s3=liczba1.getText();
                if((s3.compareTo("0"))!=0)
                liczba1.setText(s3+"8");
                else  
                liczba1.setText("8");
                }
	}).addTo(panel);
        new Button("9", new Runnable() {
		@Override
		public void run() {
                String s3=liczba1.getText();
                if((s3.compareTo("0"))!=0)
                liczba1.setText(s3+"9");
                else  
                liczba1.setText("9");
                }
	}).addTo(panel);
        new Button("CLR", new Runnable() {
		@Override
		public void run() {
                liczba1.setText("0");
                text1.setText("0");
                liczba2.setText("----");
                liczba3.setText("----");
                operacja.setText("--");
               
                }
	}).addTo(panel);
        
        new Button("4", new Runnable() {
		@Override
		public void run() {
                String s3=liczba1.getText();
                if((s3.compareTo("0"))!=0)
                liczba1.setText(s3+"4");
                else  
                liczba1.setText("4");
                }
	}).addTo (panel);
        new Button("5", new Runnable() {
		@Override
		public void run() {
                String s3=liczba1.getText();
                if((s3.compareTo("0"))!=0)
                liczba1.setText(s3+"5");
                else  
                liczba1.setText("5");
                }
	}).addTo(panel);
        new Button("6", new Runnable() {
		@Override
		public void run() {
                String s3=liczba1.getText();
                if((s3.compareTo("0"))!=0)
                liczba1.setText(s3+"6");
                else  
                liczba1.setText("6");
                }
	}).addTo(panel);
        new Button("C", new Runnable() {
		@Override
		public void run() {
                String s3=liczba1.getText();
                int dl=s3.length();
                if(dl<=1)
                s3="0";
                else
                s3=s3.substring(0, dl-1);
                liczba1.setText(s3);
                }
	}).addTo(panel);
        new Button("1", new Runnable() {
		@Override
		public void run() {
                String s3=liczba1.getText();
                if((s3.compareTo("0"))!=0)
                liczba1.setText(s3+"1");
                else  
                liczba1.setText("1");
                }
	}).addTo (panel);
        new Button("2", new Runnable() {
		@Override
		public void run() {
                String s3=liczba1.getText();
                if((s3.compareTo("0"))!=0)
                liczba1.setText(s3+"2");
                else  
                liczba1.setText("2");
                }
	}).addTo(panel);
        new Button("3", new Runnable() {
		@Override
		public void run() {
               String s3=liczba1.getText();
                if((s3.compareTo("0"))!=0)
                liczba1.setText(s3+"3");
                else  
                liczba1.setText("3");
                }
	}).addTo(panel);
        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        new Button("0", new Runnable() {
		@Override
		public void run() {
                String s3=liczba1.getText();
                if((s3.compareTo("0"))!=0)
                liczba1.setText(s3+"0");
                else  
                liczba1.setText("0");
                }
	}).addTo(panel);
        new Button(".", new Runnable() {
		@Override
		public void run() {
                String s3=liczba1.getText();
                if(s3.indexOf(".")==(-1))
                liczba1.setText(s3+".");
                }
	}).addTo(panel);
        panel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        new Button("=", new Runnable() {
		@Override
		public void run() {
                String s3=liczba1.getText();
                String s1=liczba2.getText();
                String op=operacja.getText();
                if(op.equals("+")){
                text1.setText(dodaj(s1, s3));
                liczba3.setText(s3);
                operacja.setText("+ ");
                }
                else if (op.equals("-")){
                text1.setText(odejmij(s1, s3));
                liczba3.setText(s3);
                operacja.setText("- ");
                }
                else if (op.equals("*")){
                text1.setText(pomnoz(s1, s3));
                liczba3.setText(s3);
                operacja.setText("* ");
                }
                else if (op.equals("/")){
                text1.setText(podziel(s1, s3)); 
                liczba3.setText(s3);
                operacja.setText("/ ");
                }
                else ;
                  
                liczba1.setText("0");
                }
	}).addTo(panel);
        
         // stworzenie okna do utrzymywania panelu głównego
        BasicWindow window = new BasicWindow();
        window.setComponent(mainPanel.withBorder(Borders.doubleLine("Kalkulator")));
        panel.addComponent(new EmptySpace());
        
        // Utworzenie i start interfejsu użytkownika
        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLACK));
        gui.addWindowAndWait(window);
   }
}