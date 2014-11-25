
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package outils;

//~--- JDK imports ------------------------------------------------------------

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author armel
 */
public class Nombre {

    /**
     * arrondi un Double au rang superieur
     * @param nombre
     * @param nbApVirg
     * @return
     */
    public static double arrondir(Double nombre, double nbApVirg) {
        return (double) ((int) (nombre * Math.pow(10, nbApVirg) + .5)) / Math.pow(10, nbApVirg);
    }

    /**
     * formatte un decimal en format local(fr)
     * @param nombre
     * @return
     */
    public static String formatFr(Double nombre) {
        NumberFormat formatter = DecimalFormat.getInstance(Locale.FRENCH);

        try {
            String nombreFr = formatter.format(nombre);

            // System.out.println("Number in fr: " + nombreFr);
            return nombreFr;
        } catch (NumberFormatException e) {
            e.printStackTrace();

            return null;
        }
    }

    /*
     *  formatte un decimal fr en decimal avec point(.)
     * @param nombreFr
     *
     */
    public static Double formatNumber(String nombreFr) {
        try {
            NumberFormat formatter = DecimalFormat.getInstance(Locale.FRENCH);
           // Double       nombre    = (Double) formatter.parse(nombreFr);
             //Double       nombre    = 
         Number number = formatter.parse(nombreFr);
         formatter.setMinimumFractionDigits(2);
         formatter.setMaximumFractionDigits(2);
    Double d = number.doubleValue();
            return d;
        } catch (ParseException ex) {
            Logger.getLogger(Nombre.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        }
    }
    
    
    
    
    /**
     * formatte un decimal en format local(fr)
     * @param nombre en decimal à convertir en fr
     * @return le nombre en string formatté en fr
     */
    public static String outPutNumber(Double nombre) {
    DecimalFormat ft = new DecimalFormat("#.000");
       return ft.format(nombre);
   }
    
  /**
     * convertit un nombre en string fr vers un decimal 
     * @param nombreFr le decimal fr à convertir en 
     * @return decimal standart
     */
    public static Double intPutNumber(String nombreFr) {
    DecimalFormat ft = new DecimalFormat("#.000");
      //String montantTexte = "";
        Double montant;
        try {
            return ft.parse(nombreFr).doubleValue();
        } catch (ParseException ex) {
            //Logger.getLogger(Nombre.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   }  
    

    /**
     * Arrondi d'un double avec n éléments après la virgule.
     * @param a La valeur à convertir.
     * @param n Le nombre de décimales à conserver.
     * @return La valeur arrondi à n décimales.
     */
    public static double floor(double a, int n) {
        double p = Math.pow(10.0, n);

        return Math.floor((a * p) + 0.5) / p;
    }
    
    /**
     *  valide un decimal(chaîne carc.) français
     * @param number nombre decimal avec virgule en string
     * @return true si le nombre est valide
     */
    
    public static String validateNumber( String number ){
       String  res;
   res= number.replaceAll("\\s+", ""); //supprimes les espaces dans un nombre
   if(res.matches("^\\d+\\,?\\d{0,3}$"))
      return res;
   return null;
    }
}

  

//~ Formatted by Jindent --- http://www.jindent.com
