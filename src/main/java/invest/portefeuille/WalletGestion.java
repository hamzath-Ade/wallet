package invest.portefeuille;

import java.util.Random;

public class WalletGestion {
    //Va contenir tout ce que l'utilisateur possede comme investissement
    // du coup un wallet type est composé du nom d'utilisateur, d'un token, mot de passe,
    // valeur de ces differents actifs


    static Random random = new Random();
    private final String owner;
    private  int token = random.nextInt(100);
    private  double valeurPortefeuille = 0.0;
    private String description;

    //CONSTRUCTEUR
    public WalletGestion(String owner, String description) {
        this.owner = owner;
        this.description = description;
    }

    //Les getters
    public String getOwner() {
        return owner;
    }
    public   int getToken(){
        return token;
    }
}
