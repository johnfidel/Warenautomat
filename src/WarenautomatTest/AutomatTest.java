package WarenautomatTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

import warenautomat.*;

public class AutomatTest 
{
  
  public static void main(String[] args) throws ParseException {

    DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.GERMAN);
    {
      SystemSoftware.output(false);
      
      Automat automat = new Automat();
      
      automat.fuelleFach(1, "Mars", 2.50, df.parse("01.01.2017"));
      automat.drehen();
      
      automat.fuelleFach(3, "Snickers", 3.50, df.parse("02.02.2017"));
      automat.drehen();
      
      // Die Drehtellerposition auf den Anfang setzten
      while (!(automat.gibAktuelleDrehtellerPosition() == 1))
      {
        automat.drehen();
      }
      
      // alle 16 fächer iterieren
      for (int fachNr = 0; fachNr < 16; fachNr++)
      {
        System.out.println("Fach #" + (fachNr + 1));
      
        for (int drehtellerNr = 0; drehtellerNr < 7; drehtellerNr++)
        {
          Fach aktuellesFach = automat.gibFachVorDerTuer(drehtellerNr);
         
          Ware aktuelleWare = aktuellesFach.GetWare();
         
          if (aktuelleWare != null)
          {
            System.out.println("Drehteller #" + (drehtellerNr + 1) + " " + aktuelleWare.Name() + " " +
                                                                    aktuelleWare.Preis() + " " + 
                                                                    aktuelleWare.AblaufDatum());
          }
          else
          {
            System.out.println("Drehteller #" + (drehtellerNr + 1) + " ist leer.");            
          }
        }
        
        automat.drehen();
      }
      
      // nun die Verkaufsstatistik abfragen
      
      // gesammt warenbetrag abfragen
      System.out.println("Gesammt Warenwert: " + automat.gibTotalenWarenWert() + " " + SystemSoftware.gibAktuellesDatum());
           
      //Datum ändern
      SystemSoftware.setzeAktuellesDatum(df.parse("01.01.2020"));
      
      // gesammt warenbetrag abfragen
      System.out.println("Gesammt Warenwert: " + automat.gibTotalenWarenWert() + " " + SystemSoftware.gibAktuellesDatum());
          
      // preis der Ware Ändern
      automat.fuelleFach(4, "Mars", 3.50, df.parse("02.01.2017"));

      //Datum auf heute
      SystemSoftware.setzeAktuellesDatum(df.parse("01.01.2000"));

      // gesammt warenbetrag abfragen
      System.out.println("Gesammt Warenwert: " + automat.gibTotalenWarenWert() + " " + SystemSoftware.gibAktuellesDatum());

      //Datum auf heute
      SystemSoftware.setzeAktuellesDatum(df.parse("01.01.2020"));

      // gesammt warenbetrag abfragen
      System.out.println("Gesammt Warenwert: " + automat.gibTotalenWarenWert() + " " + SystemSoftware.gibAktuellesDatum());
      
    }
  
  }

}
