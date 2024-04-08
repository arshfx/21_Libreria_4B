/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilita;

import eccezioni.FileException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Studente
 */
public class TextFile {
    
    private char mode;
    private BufferedReader reader;
    private BufferedWriter writer;
    
    public TextFile(String fileName, char mode) throws FileNotFoundException, IOException{
        
        this.mode='R';
        if(mode=='W' || mode=='w')
            this.mode='W';
        
        if(this.mode=='R')
            reader=new BufferedReader(new FileReader(fileName));
        else
            writer=new BufferedWriter(new FileWriter(fileName));
        
    }
    
    public TextFile(String fileName, char mode, boolean append) throws FileNotFoundException, IOException{
        
        this.mode='R';
        if(mode=='W' || mode=='w')
            this.mode='W';
        
        if(this.mode=='R')
            reader=new BufferedReader(new FileReader(fileName));
        else
            writer=new BufferedWriter(new FileWriter(fileName, append));
        
    }
    
    public void toFile(String line) throws FileException, IOException{
        if(this.mode=='R')
            throw new FileException("ERRORE::file aperto in lettura");
        
        writer.write(line);
        writer.newLine();
    }
    
    public String fromFile() throws FileException, IOException{
        String s;
        if(this.mode=='W')
            throw new FileException("ERRORE::file aperto in scrittura");
        
        s=reader.readLine();
        if(s==null)
            throw new FileException("");
        
        return s;
    }
    
    public void Close() throws IOException{
        if(this.mode=='R')
            reader.close();
        if(this.mode=='W')
            writer.close();
    }
    
    
    
}
