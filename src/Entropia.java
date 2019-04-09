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
import java.util.Scanner;
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
            RandomAccessFile arch; //= new RandomAccessFile("capPNG.png", "r");
            int [] frec = new int [256];
            double [] prob = new double [256];
            double len; //= arch.length();
            double cont;
            double res;
            int pos;
            
            for (int i = 0; i < 256; i++) {
                frec[i] = 0;
            }
            
            Scanner lee = new Scanner(System.in);
            String lec = "";
            
            while(!lec.equalsIgnoreCase("N")){
                
                System.out.println("Escriba el nombre del archivo a analizar");
                
                lec = lee.nextLine();
                
                arch = new RandomAccessFile(lec, "r");
                len = arch.length();
                
                System.out.println("Escriba el nombre del archivo donde se guardarán los resultados");
                
                lec = lee.nextLine();
                
                for (int i = 0; i < 256; i++) {
                    frec[i] = 0;
                    prob[i] = 0;
                }
                
                cont = 0;
                res = 0;
                
                try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(lec), "utf-8"))) {
                
                    while(cont<len){
                        pos = arch.read();
                        frec[pos] = frec[pos]+1;
                        cont++;
                    }
                
                
                    for (int i = 0; i < 256; i++) {
                        prob[i] = frec[i]/len;                
                        res += prob[i]*Math.log(prob[i])/Math.log(2);
                    
                        writer.write("Probabiliad del símbolo " + i + ": "+ prob[i]+ System.lineSeparator());
                    }
                
                    res = -1*res;
                
                    writer.write("El valor de la entropía en el archivo es de: " + res);
                
                    System.out.println("Desea analizar otro archivo?(Y/N)");
                    
                    lec = lee.nextLine();
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR");
            Logger.getLogger(Entropia.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
}
