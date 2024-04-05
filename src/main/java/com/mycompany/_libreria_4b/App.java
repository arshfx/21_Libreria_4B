/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany._libreria_4b;

import eccezioni.EccezionePosizioneNonValida;
import eccezioni.EccezionePosizioneOccupata;
import eccezioni.EccezionePosizioneVuota;
import eccezioni.EccezioneRipianoNonValido;
import java.io.IOException;
import utilita.ConsoleInput;
import utilita.Menu;

/**
 *
 * @author Io
 */
public class App {

    public static void main(String[] args) 
    {
        int numeroVociMenu=7;
        String[] vociMenu=new String[numeroVociMenu];
        int voceMenuScelta;
        Menu menu;
        Scaffale s1=new Scaffale();
        //Scanner tastiera=new Scanner(System.in);
        ConsoleInput tastiera=new ConsoleInput();
        String titolo,autore;
        int numeroPagine,ripiano, posizione;
        int esito;
        Libro lib = null;
        Libro[] elencoLibriOrdinatiAlfabeticamente;
        String [] elencoTitoliAutore = null;
        
        vociMenu[0]="0 -->\tEsci";
        vociMenu[1]="1 -->\tVisualizza tutti i volumi dello scaffale";
        vociMenu[2]="2 -->\tAggiungi volume (ripiano,posizione)";
        vociMenu[3]="3 -->\tCerca volume (ripiano, posizione)";
        vociMenu[4]="4 -->\tElimina volume (ripiano,posizione)";
        vociMenu[5]="5 -->\tMostra titoli di uno specifico autore";
        vociMenu[6]="6 -->\tMostra elenco dei volumi presenti ordinato alfabeticamente per titolo";
        
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
                    
            }
        }while(voceMenuScelta!=0);
        
    }
}
