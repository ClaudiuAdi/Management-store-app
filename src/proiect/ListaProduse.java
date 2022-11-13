package proiect;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ListaProduse implements Serializable {
    private List<Produs> produse;

    private static ListaProduse listaProduse = null;
    private static final long serialVersionUID = 1L;
    public static Map<Integer,Produs> produseMap;

    private ListaProduse() {
        this.produse = new ArrayList<>();
    }

    public List<Produs> getProduse() {
        return produse;
    }

    public void adaugaProdusNou(Produs produs) {
        this.produse.add(produs);
    }

//    @Override
//    public String toString() {
//        return "Lista produse{" +
//                "produse=" + produse +
//                '}';
//    }

    public static ListaProduse getInstance() {
        if(listaProduse == null) {
            listaProduse = new ListaProduse();
        }
        return listaProduse;
    }

    public void salveazaListaDeProduse() {
        Fisier fisier = new Fisier("listaProduse.dat");
        try {
            fisier.open();
            fisier.serializareListaDeProduse(listaProduse);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void populeazaListaDeProduse() {
        Fisier fisier = new Fisier("listaProduse.dat");
        try {
            fisier.open();
            if(!fisier.getFisier().exists()) {
                return;
            }
            fisier.deserializareListaProduse(listaProduse);
            produseMap = new LinkedHashMap<>();
            int i = 1;
            for(Produs produs : listaProduse.getProduse()) {
                produseMap.put(i, produs);
                i++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}