package com.academy.lesson13;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ReadFromPDF {
    public static void main(String[] args) throws IOException {


        //String path="Acrobat.exe /A search=Join D:\\p1.pdf";
   /*это работает , но просто открывает без параметров
        String path="D:\\p1.pdf";
        Desktop desktop = Desktop.getDesktop();
        File file = new File(path);
        desktop.open(file);

*/

       String cmds[] = new String[] {"cmd", "/c", "D:\\p1.pdf"};

     //   String cmds[] = new String[] {"cmd", "/c", "C:\\Program Files (x86)\\Adobe\\Acrobat 10.0\\Acrobat"};
        try {
            Runtime.getRuntime().exec(cmds);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //   Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler c:\\Java-    D:\\p1.pdf");
        //String commands="Acrobat.exe /A search=Join D:\\p1.pdf";
//        String commands="cmd D:\\p1.pdf";
//        String commands="cmd";

        //C:\Program Files (x86)\Adobe\Acrobat 10.0\Acrobat
/*
        Process p = Runtime.getRuntime().exec(commands);

        try {
            p.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/
    }
}
