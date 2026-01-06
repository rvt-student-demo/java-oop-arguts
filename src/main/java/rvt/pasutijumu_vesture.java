package rvt;

import java.io.File;
import java.util.Scanner;

public class pasutijumu_vesture {

    public static void main(String[] args) {
        try (Scanner reader = new Scanner(new File("data/orders.csv"))) {
            reader.nextLine();
            double total_of_all = 0;
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] dati = line.split(",");

                int quantity = Integer.parseInt(dati[3]);
                double price = Double.parseDouble(dati[4]);
                double total = quantity * price;

                System.out.println(
                        "Pasutijums #" + dati[0] +
                                ": " + dati[1] + " pasutija " +
                                dati[3] +
                                " x " + dati[2] +
                                "(" + dati[4] + ")" +
                                " -> " + "Kopa: " + total
                                + " EUR"

                );
                total_of_all += total;
            }
            System.out.println("Kopeja pasutijuma summa: " + total_of_all + " EUR");

        } catch (Exception e) {
            System.out.println("Kļūda: " + e.getMessage());
        }

    }

}
