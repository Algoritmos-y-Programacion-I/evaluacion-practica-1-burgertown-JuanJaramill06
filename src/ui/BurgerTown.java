package ui;

import java.util.Scanner;

public class BurgerTown {

    public static Scanner reader;
    public static double[] prices;
    public static int[] units;

    public static void main(String[] args) {

        initializeGlobals();
        menu();
    }


    public static void initializeGlobals() {
        reader = new Scanner(System.in);
    }


    public static void menu() {

        System.out.println("Welcome to BurgerTown!");

        setQuantitySold();

        boolean salir = false;

        do {

            System.out.println("\nMain Menu:");
            System.out.println("1. Request prices ($) and quantities of each dish sold during the day”.");
            System.out.println("2. Calculate the total amount of plates sold in a day");
            System.out.println("3. Calculate the average price of the plates sold during the day.");
            System.out.println("4. Calculate total sales (money collected) during the day");
            System.out.println("5. Consult the number of plates that have exceeded a minimum sales limit during the day.");
            System.out.println("6. Exit");
            System.out.println("\nType the option to execute");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    requestData();
                    break;
                case 2:
                    System.out.println("\nThe total number of plates sold during the day was: " + calcularTotalplatesVendidos());
                    break;
                case 3:
                    System.out.println("\nThe average price of the plates sold during the day was: " + calcularPrecioaverage());
                    break;
                case 4:
                    System.out.println("\nTotal sales (money collected) during the day were as follows: " + calculateSalesTotals());
                    break;
                case 5:
                    System.out.println("\nEnter the minimum sales limit to analyze");
                    double limite = reader.nextDouble();
                    System.out.println("\nOf the " + prices.length + " references sold during the day, " + consultplatesAboutLimit(limite) + " superaron el limite minimo de ventas de " + limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;

                default:
                    System.out.println("\nOpcion invalida, intenta nuevamente.");
                    break;
            }

        } while (!salir);

    }

    public static void setQuantitySold() {

        System.out.println("\nEnter the number of different plates sold during the day. ");
        int plates = reader.nextInt();
        prices = new double[plates];
        units = new int[plates];

    }

    public static void requestData() {
        for (int i = 0; i < prices.length; i++) {
            System.out.println("\nEnter the price of the dish " + (i + 1));
            prices[i] = reader.nextDouble();
            System.out.println("Enter the quantity sold of the plate " + (i + 1));
            units[i] = reader.nextInt();
        }
    }

    public static int calcularTotalplatesVendidos() {
        int quantityTotal = 0;
        for (int unidad : units) {
            quantityTotal += unidad;
        }
        return quantityTotal;
    }

    /**
     * Description: The calculatePriceaverage method calculates the price averages of the plates sold during the day.
     * pre: The prices and units arrays must be initialized
     * pos: Returns the price average 
     * @return The number of average
     */
    public static double calcularPrecioaverage() {
        double priceTotal = 0;
        int platesTotales = 0;
        double average = 0;
        for (int i = 0; i < prices.length; i++) {
            priceTotal += prices[i] * units[i];
            platesTotales += units[i];
        }
        average = priceTotal / platesTotales;
        return average;
    }

    /**
     * Descripcion: The calculateSalesTotals method calculates the total sales made during the day. 
     * pre: The prices and units arrays must be initialized
     * pos: Returns total sales 
     * @return The number of salesTotals 
     */
    public static double calculateSalesTotals() {
        double salesTotals = 0;
        for (int i = 0; i < prices.length; i++) {
            salesTotals += prices[i] * units[i];
        }
        return salesTotals;
    }

    /**
     * Descripcion: The consultplatesAboutLimit method calculates which units have exceeded the limit set by the user.
     * pre: The prices and units arrays must be initialized
     * pos: Returns the number of plates exceeding the minimum sales limit
     * @param limit Minimum sales given by the limit
     * @return Number of plates exceeding the limit 
     */
    public static int consultplatesAboutLimit(double limite) {
        int counter = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] * units[i] > limite) {
                counter++;
            }
        }
        return counter;
    }
}
