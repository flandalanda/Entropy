/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis Landa
 */
public class Entropia {
    
    public static void main(String[] args) throws IOException{
        try {
            //String path = System.getProperty("user.dir");
            RandomAccessFile arch = new RandomAccessFile("capPNG.png", "r");
            int [] frec = new int [256];
            double [] prob = new double [256];
            double len = arch.length();
            double cont = 0;
            double res = 0;
            int pos;
            
            for (int i = 0; i < 256; i++) {
                frec[i] = 0;
            }
            
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream("res.csv"), "utf-8"))) {
                
                while(cont<len){
                    pos = arch.read();
                    frec[pos] = frec[pos]+1;
                    cont++;
                }
                
                
                for (int i = 0; i < 256; i++) {
                    prob[i] = frec[i]/len;                
                    res += prob[i]*Math.log(prob[i])/Math.log(2);
                    
                    writer.write("Probabiliad de valor " + i + ": "+ prob[i]+", ");
                }
                
                res = -1*res;
                
                System.out.println(len);
                
                for (double i : prob) {
                    System.out.println(i);
                }
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR");
            Logger.getLogger(Entropia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
