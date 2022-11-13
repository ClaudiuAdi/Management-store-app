package proiect;

import java.io.*;
import java.util.LinkedList;

public class Fisier {
    private File fisier;
    private String numeFisier;

    public Fisier(String numeFisier) {
        this.numeFisier = numeFisier;
    }

    public File getFisier() {
        return fisier;
    }

    public void open() {
        fisier = new File(numeFisier);
    }

    public void serializareListaDeProduse(ListaProduse lista) {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.numeFisier));
            objectOutputStream.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null)
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public void deserializareListaProduse(ListaProduse produse) {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(this.numeFisier));
            produse = (ListaProduse) objectInputStream.readObject();
            for (Produs produs : produse.getProduse()) {
                ListaProduse.getInstance().adaugaProdusNou(produs);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null)
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public void genereazaFisierRaport(LinkedList<Produs> produseProducator) {
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        try {
            this.open();
            if (!this.getFisier().exists()) {
                fileWriter = new FileWriter(this.numeFisier);
                printWriter = new PrintWriter(fileWriter);
                for (Produs produs : produseProducator) {
                    printWriter.println(produs.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                    printWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
