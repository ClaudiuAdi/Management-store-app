package proiect;

import java.io.Serializable;
import java.util.UUID;

public class Produs implements Serializable {
    private final String cod;
    private String numeProdus;
    private String producator;
    private TipProdus tipProdus;
    private int garantie;
    private int stoc;

    private static final long serialVersionUID = 1L;

    public Produs(String numeProdus, String producator, TipProdus tipProdus, int garantie, int stoc) {
        this.cod = UUID.randomUUID().toString();
        this.numeProdus = numeProdus;
        this.producator = producator;
        this.tipProdus = tipProdus;
        this.garantie = garantie;
        this.stoc = stoc;
    }

    public String getCod() {
        return cod;
    }

    public String getNumeProdus() {
        return numeProdus;
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    public String getProducator() {
        return producator;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }

    public TipProdus getTipProdus() {
        return tipProdus;
    }

    public void setTipProdus(TipProdus tipProdus) {
        this.tipProdus = tipProdus;
    }

    public int getGarantie() {
        return garantie;
    }

    public void setGarantie(int garantie) {
        this.garantie = garantie;
    }

    public int getStoc() {
        return stoc;
    }

    public void setStoc(int stoc) {
        this.stoc = stoc;
    }

    @Override
    public String toString() {
        return "Produsul '" + this.numeProdus + '\'' +
                ", cod='" + this.cod + '\'' +
                ", produs de catre '" + this.producator + '\'' +
                ", tipProdus=" + this.tipProdus +
                ", stoc='" + this.stoc +
                ", garantie=" + this.garantie;
    }
}