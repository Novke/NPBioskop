/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.np.ac.bg.bioskop.common2.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Klasa koja se koristi za parsiranje datuma i vremena.
 */
public class DateParser {
/**
 * Formator datuma koji ne ukljucuje informacije o trenutnom vremenu
 */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     * Formator datuma koji ukljucuje informacije o trenutnom vremenu
     */
    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Pretvara datum u formatiranu string reprezentaciju.
     *
     * @param date datum koji se formatira
     * @return formatirana string reprezentacija datuma
     */
    public static String toString(Date date) {
        return (date == null ? "NULL" : dateFormat.format(date));
    }

    /**
     * Pretvara SQL datum u Java.util.Date objekat.
     *
     * @param date SQL datum koji se konvertuje
     * @return Java.util.Date objekat
     */
    public static Date sqlDateToUtilDate(java.sql.Date date) {
        return new Date(date.getTime());
    }

    /**
     * Pretvara datum i vreme u formatiranu string reprezentaciju.
     *
     * @param date datum i vreme koje se formatira
     * @return formatirana string reprezentacija datuma i vremena
     */
    public static String timeToString(Date date) {
        return (date == null ? "NULL" : dateTimeFormat.format(date));
    }
}
