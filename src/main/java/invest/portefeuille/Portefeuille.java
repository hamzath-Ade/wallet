package invest.portefeuille;

public abstract class Portefeuille {

    public double getMesBitcoin() {
        return mesBitcoin;
    }

    public void setMesBitcoin(double mesBitcoin) {
        this.mesBitcoin = mesBitcoin;
    }

    public double getMesActionApple() {
        return mesActionApple;
    }

    public void setMesActionApple(double mesActionApple) {
        this.mesActionApple = mesActionApple;
    }

    public double mesBitcoin;
    public double mesActionApple;



}
