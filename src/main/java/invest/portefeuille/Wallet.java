package invest.portefeuille;

import java.util.Random;

public class Wallet {
    //Va contenir tout ce que l'utilisateur possede comme investissement
    // du coup un wallet type est composé du nom d'utilisateur, d'un token, mot de passe,
    // valeur de ces differents actifs


    static Random random = new Random();
    private final String owner;
    private  int token;


    //CONSTRUCTEUR
    public Wallet(String owner) {
        this.owner = owner;

    }
}
