package assembling.parsing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MakeRegEx {
    public static void main(String[] args) {
        System.out.print("Enter a filename: ");
        Scanner scanner = new Scanner(System.in);
        String strFile = scanner.nextLine();
        File file = new File(strFile);
        try {
            scanner = new Scanner(file);
        } catch(FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }

        while(scanner.hasNextLine()) {
            String str = scanner.nextLine();
            str = str.toUpperCase();
            StringBuilder out = new StringBuilder();
            out.append(str);
            if(str.length() < 4) {
                out.append("\t");
            }
            out.append("\t: ");
            for(int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                out.append("[" + str.charAt(i) + (char)(c + 32) + "]");
            }
            out.append("[ ];");
            System.out.println(out);
        }
    }
}
