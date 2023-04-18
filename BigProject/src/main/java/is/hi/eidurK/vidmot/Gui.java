package is.hi.eidurK.vidmot;

import is.hi.eidurK.vinnsla.Book;
import is.hi.eidurK.vinnsla.Borrowable;

import java.util.ArrayList;

public class Gui {

    //ANSI töflu strengir
    private static String hLine = "\033[2G+-------------------------------+";
    private static String vLine = "\033[2G|\033[34G|";
    private static String down = "\033[1B";
    private static String writingPosition = "\033[4G";
    private static String newLine = down + vLine + writingPosition;
    public static void makeTable(String[] array){
        System.out.print(hLine);
        for(String s: array){
            System.out.print(newLine + s);
        }
        System.out.print(down + hLine);
        System.out.println();
    }

    public static void makeNumberedTable(String[] options){
       System.out.print(hLine);
       int i = 1;
       for(String s: options){
           System.out.print(newLine +  i + ". " + s);
           i++;
       }
       System.out.print(down + hLine);
       System.out.println();
    }

    public static void makeBookTable(ArrayList<Book> list){
        String[] s = new String[list.size()];
        for(Book b : list){
            s[list.indexOf(b)] = b.getTitle();
        }
        makeTable(s);
    }
    public static void clearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static void printRed(String s){
        System.out.println("\033[1;31m"+s + "\033[0m");
    }
    public static void printGreen(String s){
        System.out.println("\033[1;32m"+s + "\033[0m");
    }
}
