package invest.portefeuille;

import java.util.Scanner;

public class Conversion {
    //Contient toutes les valeurs des differents actif mis à jour en fonction du temps
    // Effectue les conversion entre euro et bitcoin par exemple
    //Va contenir les historique des valeur des actifs dans le temps??

    // Taux de change
    public static void main(String[] args) {
        double tauxDeChange = 0.91; // Exemple : 1 USD = 0.91 EUR
        // Demander à l'utilisateur la quantité et la devise
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez la quantité : ");
        double montant = scanner.nextDouble();

        System.out.print("Entrez la devise (par exemple, USD) : ");
        String devise = scanner.next();

        // Convertir en euros
        double montantEnEuros = convertirEnEuros(montant, tauxDeChange);

        // Afficher le résultat
        System.out.println(montant + " " + devise + " équivaut à " + montantEnEuros + " EUR.");
    }

    private static double convertirEnEuros(double montant, double tauxDeChange) {
        return montant*tauxDeChange;
    }


}
