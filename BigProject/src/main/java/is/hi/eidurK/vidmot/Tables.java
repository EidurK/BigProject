package is.hi.eidurK.vidmot;

public class Tables {

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
    public static void clearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}
