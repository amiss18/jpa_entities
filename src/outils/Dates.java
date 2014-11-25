/*
 * classe convertissant les dates( dateFR, dateUS)
 */
package outils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author armel
 */
public class Dates {

    /**
     *
     */
    public static final String paternUS="yyyy-MM-dd";
    
    public Dates(){
        
    }
    
    /* Convertit une chaîne de caractère en un objet de type Date
    *@param date  la date entrée
    *@return  l'objet date
    *@see Date
    */
    public  static java.util.Date  convertDateFrToUs(String dateFr ){
         SimpleDateFormat formatFr=null;
         java.util.Date  sortieDateFr=null;
         formatFr = new SimpleDateFormat("dd-MM-yyyy");
        try {
        
         
         sortieDateFr=formatFr.parse(dateFr);
        
         

  } catch (ParseException ex) {
        System.out.println("Parse Exception");
  }
         return sortieDateFr;
    }
    
    
    /*
    * convertit une date de type chaîne de caractère en un objet Dates
    */
    
     public  static java.util.Date  convertStringToDate(String date ){
         SimpleDateFormat format=null;
         java.util.Date  sortieDateFr=null;
         format = new SimpleDateFormat("yyyy-MM-dd");
        try {
        
         
         sortieDateFr=format.parse(date);
        
         

  } catch (ParseException ex) {
        System.out.println("Parse Exception");
  }
         return sortieDateFr;
    }
     
     
     /**
     * Transforme un objet Date en une date fr(type String)
     * 
     * @param date
     * @return String la date fr en chaîne de caractères
     */
     
       public  static String  convertDateToDateFr(Date date ){
        SimpleDateFormat formatFr = new SimpleDateFormat("dd-MM-yyyy");
         return formatFr.format(date);
    }
       
 
	public static boolean isThisDateValid(String dateToValidate, String dateFromat){
 
		if(dateToValidate == null){
			return false;
		}
 
		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);
 
		try {
 
			//if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
			System.out.println(date);
                        return true;
 
		} catch (ParseException e) {
 
			//e.printStackTrace();
			//return false;
		}
 
	return false;
	}
 

       
}
