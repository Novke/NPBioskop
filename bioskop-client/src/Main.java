import forms.Login;

import javax.swing.*;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        new Main().funkcija();
    }
    private boolean isWithinNext7Days(Date pocetakProjekcije) {
        LocalDate currentDate = LocalDate.now();
        LocalDateTime startDateTime = pocetakProjekcije.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime now = LocalDateTime.of(currentDate, LocalTime.MIDNIGHT);
        LocalDateTime endDate = now.plusDays(7); // Add 8 days to include the entire 7th day

        return startDateTime.isBefore(endDate) && startDateTime.isAfter(now);
    }

    private void funkcija() {

        Date datum = new Date(123,6,12,0,0);
        System.out.println("Datum: " + datum);
        boolean bool = isWithinNext7Days(datum);

        System.out.println(bool);

        JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti ocenu");





    }
}