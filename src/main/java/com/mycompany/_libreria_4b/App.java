/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany._libreria_4b;

import eccezioni.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilita.ConsoleInput;
import utilita.Menu;
import utilita.TextFile;

/**
 *
 * @author Io
 */
public class App {

    public static void main(String[] args) 
    {
        int numeroVociMenu=9;
        String[] vociMenu=new String[numeroVociMenu];
        int voceMenuScelta;
        Menu menu;
        Scaffale s1=new Scaffale();
        //Scanner tastiera=new Scanner(System.in);
        ConsoleInput tastiera=new ConsoleInput();
        String titolo,autore;
        int numeroPagine,ripiano, posizione;
        Libro lib;
        Libro[] elencoLibriOrdinatiAlfabeticamente;
        String [] elencoTitoliAutore = null;
        String nomeFileCSV="volumi.csv";
        TextFile file = null;
        String nomeFileBinario = "scaffale.bin";
        
        vociMenu[0]="0 -->\tEsci";
        vociMenu[1]="1 -->\tVisualizza tutti i volumi dello scaffale";
        vociMenu[2]="2 -->\tAggiungi volume (ripiano,posizione)";
        vociMenu[3]="3 -->\tCerca volume (ripiano, posizione)";
        vociMenu[4]="4 -->\tElimina volume (ripiano,posizione)";
        vociMenu[5]="5 -->\tMostra titoli di uno specifico autore";
        vociMenu[6]="6 -->\tMostra elenco dei volumi presenti ordinato alfabeticamente per titolo";
        vociMenu[7]="7 -->\tEsporta volumi in formato CSV";
        vociMenu[8]="8 -->\tImporta volumi da file CSV";
        
        menu=new Menu(vociMenu);
        
        do
        {
            System.out.println("MENU:");
            voceMenuScelta=menu.sceltaMenu();
            switch (voceMenuScelta) 
            {
                case 0:
                    System.out.println("Arrivederci");
                    break;
                case 1:
                    System.out.println(s1.toString());
                    break;
                case 2:
                    try{
                        System.out.println("Titolo --> ");
                        titolo=tastiera.readString();
                        System.out.println("Autore --> ");
                        autore=tastiera.readString();
                        
                        
                        do{
                            try{
                                System.out.println("Numero pagine --> ");
                                numeroPagine=tastiera.readInt();  
                                break;
                            }
                            catch(NumberFormatException e){
                                System.out.println("ERRORE::NumberFormatException - formato non valido");
                            }
                        }while(true);

                        do{
                            try{
                                System.out.println("Ripiano (0..4) --> ");
                                ripiano=tastiera.readInt();
                                break;
                            }
                            catch(NumberFormatException e){
                                
                            }
                        }while(true);
                        
                        do{
                            try{
                                System.out.println("Posizione (0..14) --> ");
                                posizione=tastiera.readInt();
                                break;
                            }
                            catch(NumberFormatException e){
                                
                            }
                        }while(true);
                        lib=new Libro(titolo,autore,numeroPagine);
                        s1.setLibro(lib, ripiano, posizione);
                        System.out.println("Volume inserito correttamente.");
                    }
                    catch(IOException e){
                        
                    }
                    catch (EccezioneRipianoNonValido ex) 
                    {
                        System.out.println("Ripiano non valido!");
                    } 
                    catch (EccezionePosizioneNonValida ex) 
                    {
                        System.out.println("Posizione non valida!");
                    } 
                    catch (EccezionePosizioneOccupata ex) 
                    {
                        System.out.println("Posizione occupata!");
                    }         
                   break;

                case 3:
                    try{
                        do{
                            try{
                                System.out.println("Ripiano (0..4) --> ");
                                ripiano=tastiera.readInt();
                                break;
                            }
                            catch(NumberFormatException e){
                                
                            }
                        }while(true);
                        do{
                            try{
                                System.out.println("Posizione (0..14) --> ");
                                posizione=tastiera.readInt();
                                break;
                            }
                            catch(NumberFormatException e){
                                
                            }
                        }while(true);
                        
                        lib=s1.getLibro(ripiano, posizione);
                        System.out.println("Libro cercato:\n"+lib.toString());
                    }
                    catch(IOException e){
                        
                    }
                    catch (EccezioneRipianoNonValido e) 
                    {
                        System.out.println("Ripiano non valido!");
                    } 
                    catch (EccezionePosizioneNonValida e) 
                    {
                        System.out.println("Posizione non valida!");
                    } 
                    catch (EccezionePosizioneVuota e)
                    {
                        System.out.println("Nessun volume presente in quel ripiano/posizione!");
                    }
                    break;
                    
                case 4:
                    try{
                        do{
                            try{
                                System.out.println("Ripiano (0..4) --> ");
                                ripiano=tastiera.readInt();
                                break;
                            }
                            catch(NumberFormatException e){
                                
                            }
                        }while(true);
                        do{
                            try{
                                System.out.println("Posizione (0..14) --> ");
                                posizione=tastiera.readInt();
                                break;
                            }
                            catch(NumberFormatException e){
                                
                            }
                        }while(true);
                        s1.rimuoviLibro(ripiano, posizione);
                        System.out.println("Il libro è stato rimosso correttamente");
                    }
                    catch(IOException e){
                        
                    }
                    catch (EccezioneRipianoNonValido ex) 
                    {
                          System.out.println("Ripiano non valido");
                    } 
                    catch (EccezionePosizioneNonValida ex) 
                    {
                         System.out.println("Posizione non valida");
                    } 
                    catch (EccezionePosizioneVuota ex) 
                    {
                         System.out.println("La posizione è già vuota. Nessun libro è stto rimosso");
                    }
                  
                    break;

                case 5:
                    
                    
                    try{
                        System.out.println("Autore --> ");
                        autore=tastiera.readString();
                        elencoTitoliAutore=s1.elencoTitoliAutore(autore);
                        if (elencoTitoliAutore!=null)
                        {
                            for(int i=0;i<elencoTitoliAutore.length;i++)
                            {
                                System.out.println(elencoTitoliAutore[i]);
                            }
                        }
                        else
                            System.out.println("Nessun volume presente per l'autore scelto.");
                    }
                    catch(IOException e){

                    }
                    
                    break;
                
                case 6:
                    elencoLibriOrdinatiAlfabeticamente=s1.elencoLibriOrdinatoPerTitolo();
                    for(int i=0;i<elencoLibriOrdinatiAlfabeticamente.length;i++)
                    {
                        System.out.println(elencoLibriOrdinatiAlfabeticamente[i].toString());
                    }
                    break;
                    
                case 7: 
                    
                    try{
                        file=new TextFile(nomeFileCSV, 'W');
                        String datiVolume="";
                        for(int i=0; i<s1.getNumRipiani(); i++){
                            for(int j=0; j<s1.getNumMaxLibri(i); j++){
                                try {
                                    lib=s1.getLibro(i, j);  
                                    datiVolume=i+";"+j+";"+lib.getTitolo()+";"+lib.getAutore()+";"+lib.getNumeroPagine();
                                    file.toFile(datiVolume);
                                } 
                                catch (EccezioneRipianoNonValido | EccezionePosizioneNonValida e){
                                    //non succederà mai
                                } 
                                catch (EccezionePosizioneVuota e){
                                    //non fare nulla; vai al prossimo
                                } 
                                catch (FileException e) {
                                    System.out.println(e.toString());
                                }
                            }
                        }
                        file.Close();
                        System.out.println("Esportazione avvenuta correttamente");
                    }
                    catch(IOException e){
                        System.out.println("ERRORE IOException::errore nell'apertura del file");
                    }
                    
                    break;
                    
                case 8: 
                    String rigaLetta;
                    String[] datiVolume;
                    try{
                        file=new TextFile(nomeFileCSV, 'R');
                        
                        do{
                            try{
                                rigaLetta=file.fromFile();
                                datiVolume=rigaLetta.split(";");
                                ripiano=Integer.parseInt(datiVolume[0]);
                                posizione=Integer.parseInt(datiVolume[1]);
                                titolo=datiVolume[2];
                                autore=datiVolume[3];
                                numeroPagine=Integer.parseInt(datiVolume[4]);
                                lib=new Libro(titolo, autore, numeroPagine);
                                try{
                                    s1.setLibro(lib, ripiano, posizione);
                                }
                                catch(EccezionePosizioneNonValida e){
                                    System.out.println("ERRORE EccezionePosizioneNonValida::posizione " + posizione + " non valida" + " per il volume: "+titolo);
                                }
                                catch(EccezioneRipianoNonValido e){
                                    System.out.println("ERRORE EccezioneRipianoNonValido::ripiano " + ripiano + " non valido per il volume: "+titolo);
                                }
                                catch(EccezionePosizioneOccupata e){
                                    System.out.println("ERRORE EccezionePosizioneOccupata::nel ripiano " + ripiano + "posizione " + posizione + " già occupata. Il volume: "+titolo + " non sara posizionato");
                                }
                            }
                            catch (FileException e){
                                file.Close();
                                System.out.println("fine operazione di caricamento");
                                break;
                            }
                        }while(true);
                        
                    }
                    
                    catch(IOException e){
                        System.out.println("ERRORE IOException::impossibile aprire il file");
                    } 
                    
                    
                    break;
                    
                    case 9: //serializzazione
                    try {
                
                        ObjectOutputStream writer=new ObjectOutputStream(new FileOutputStream(nomeFileBinario));
                        writer.writeObject(s1);
                        writer.flush();
                        writer.close();
                        System.out.println("salvataggio avvenuto correttamente");
                    } 
                    catch (FileNotFoundException e){
                        System.out.println("ERRORE FileNotFoundException::file non trovato");
                    } 
                    catch (IOException e){
                        System.out.println("ERRORE IOException::impossibile accedere al file");
                    }    

                    break;
                    
                case 10: 
                    try {
                        ObjectInputStream reader=new ObjectInputStream(new FileInputStream(nomeFileBinario));
                        s1=(Scaffale)reader.readObject();
                        reader.close();
                    } 
                    catch (FileNotFoundException e){
                        System.out.println("ERRORE FileNotFoundException::file non trovato");
                    } 
                    catch (IOException e){
                        System.out.println("ERRORE IOException::impossibile accedere al file");
                    } catch (ClassNotFoundException e) {  
                        System.out.println("ERRORE ClassNotFoundException::Impossibile leggere il dato memorizzato");
                    }
                
                    break;


                    
            }
        }while(voceMenuScelta!=0);
        
    }
}
