package proiect;

import java.io.IOException;
import java.util.*;

import static java.lang.System.exit;

public class Main {

    static List<Map.Entry<Integer, Produs>> entryList;

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        int optiune = 1;

        ListaProduse listaProduse = ListaProduse.getInstance();
        listaProduse.populeazaListaDeProduse();
        entryList = new ArrayList<>(ListaProduse.produseMap.entrySet());

        //        Produs prod = new Produs("Iphone", "Apple", TipProdus.ELECTRONICE, 12,5);
//        listaProduse.adaugaProdusNou(prod);
//        listaProduse.salveazaListaDeProduse();
//        exit(0);

        System.out.println("///////////////////////////////////////////");
        System.out.println("/// BINE ATI VENIT IN APLICATIA E-ADMIN ///");
        System.out.println("///////////////////////////////////////////");

        String[] optiuni = {"0- Paraseste E-ADMIN 0",
                "1- Afiseaza toate produsele 1",
                "2- Adauga un produs nou 2",
                "3- Actualizeaza produs 3",
                "4- Sterge produs 4",
                "5- Raport produse pentru un producator 5",
                "6- Raport produse pentru un tip 6",

        };

        while (optiune != 0) {
            printeazaMenu(optiuni);
            try {
                optiune = scanner.nextInt();
                switch (optiune) {
                    case 0:
                        listaProduse.salveazaListaDeProduse();
                        System.out.println("Modificari salvate!");
                        exit(0);
                        break;
                    case 1:
//                        afisare produse
                        if (listaProduse.getProduse().isEmpty()) {
                            System.out.println("Nu exista produse");
                        } else {
                            for (Produs produs : listaProduse.getProduse()) {
                                System.out.println(produs.toString());
                            }
                        }
                        break;
                    case 2:
//                        creare produs
                        System.out.println("Apasa x pentru a anula");
                        Produs produs = creeazaProdus();
                        if (produs != null) {
                            listaProduse.adaugaProdusNou(produs);
                            ListaProduse.produseMap.put(entryList.get(entryList.size() - 1).getKey() + 1, produs);
                            entryList = new ArrayList<>(ListaProduse.produseMap.entrySet());
                        }
                        break;
                    case 3:
                        actualizeazaProdus();
                        break;
                    case 4:
                        if (listaProduse.getProduse().isEmpty()) {
                            System.out.println("Nu exista produse");
                        } else {
                            int i = 0;
                            for (Produs produsDeSters : listaProduse.getProduse()) {
                                i++;
                                System.out.println(i + "|" + produsDeSters.toString());
                            }
                        }
                        stergeProdus();
                        break;
                    case 5:
                        raportProduseProducator();
                        break;
                    case 6:
                        raportProduseTip();
                        break;
                    default:
                        System.out.println("Optiune inexistenta!");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Introduceti o valoare intre 1 si " + (optiuni.length - 1));
                scanner.next();
            }
        }
    }

    public static void printeazaMenu(String[] optiuni) {
        for (String optiune : optiuni) {
            System.out.println(optiune);
        }
        System.out.print("Alege optiunea care doresti sa se realizeze: ");
    }

    public static Produs creeazaProdus() {
        System.out.print("Nume produs  = ");
        String numeProdus = new Scanner(System.in).nextLine();
        if (numeProdus.equals("x")) {
            return null;
        }

        System.out.print("Producator = ");
        String producator = new Scanner(System.in).nextLine();
        if (producator.equals("x")) {
            return null;
        }

        System.out.println("Apasa 0 pentru a anula");
        for (TipProdus tip : TipProdus.genProdusMap.values()) {
            System.out.println(tip.name());
        }
        System.out.print("Tip produs = ");
        String tip = new Scanner(System.in).nextLine();
        if (tip.equals("x")) {
            return null;
        }
        TipProdus tipProdus;
        while (true) {
            if (TipProdus.genProdusMap.containsKey(tip)) {
                tipProdus = TipProdus.genProdusMap.get(tip);
                break;
            } else {
                System.out.println("---------------Valoare incorecta!---------------");
                System.out.println("Introdu o noua valoare");
                tip = new Scanner(System.in).nextLine();
            }
        }

        System.out.print("stoc = ");
        int stoc;
        stoc = new Scanner(System.in).nextInt();
        if (stoc == 0) {
            return null;
        }

        System.out.print("garantie = ");
        int garantie;
        garantie = new Scanner(System.in).nextInt();
        if (garantie == 0) {
            return null;
        }
        System.out.println("Produsul a fost adaugat cu succes");
        return new Produs(numeProdus, producator, tipProdus, garantie, stoc);
    }

    public static void actualizeazaProdus() {
        System.out.println("Apasa 0 pentru a anula");
        if (ListaProduse.getInstance().getProduse().isEmpty()) {
            System.out.println("Nu exista produse");
        } else {
            ListaProduse.produseMap.forEach((key, value) -> {
                System.out.println(key + " - " + value);
            });
            System.out.print("Cod produs = ");
            int cod;
            while (true) {
                try {
                    cod = new Scanner(System.in).nextInt();
                    if (cod < 0 || cod > entryList.get(entryList.size() - 1).getKey()) {
                        throw new InvalidValueException("Cod incorect");
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("----------------Valoare incorecta! Introdu un cod!----------------");
                } catch (InvalidValueException e) {
                    System.out.println("Introdu un cod care se afla in lista");
                }
            }
            if (cod != 0) {
                System.out.println("Apasa 0 pentru a anula");
                System.out.println("1 - Nume produs = " + ListaProduse.produseMap.get(cod).getNumeProdus());
                System.out.println("2 - Producator = " + ListaProduse.produseMap.get(cod).getProducator());
                System.out.println("3 - Tip produs = " + ListaProduse.produseMap.get(cod).getTipProdus());
                System.out.println("4 - Garantie = " + ListaProduse.produseMap.get(cod).getGarantie());
                System.out.println("5 - Stoc = " + ListaProduse.produseMap.get(cod).getStoc());
                System.out.println("Ce doresti sa editezi?");
                int atribut;
                while (true) {
                    try {
                        atribut = new Scanner(System.in).nextInt();
                        if (atribut < 0 || atribut > 6) {
                            throw new InvalidValueException("Cod incorect");
                        }
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("----------------Valoare incorecta! Introdu un cod!----------------");
                    } catch (InvalidValueException e) {
                        System.out.println("Introdu un cod care se afla in lista");
                    }
                }
                if (atribut != 0) {
                    if (atribut == 1) {
                        System.out.println("Apasa x pentru a anula");
                        System.out.print("Denumire produs = ");
                        String numeProdus = new Scanner(System.in).nextLine();
                        if (!numeProdus.equals("x")) {
                            for (Produs produs : ListaProduse.getInstance().getProduse()) {
                                if (produs.getCod().equals(ListaProduse.produseMap.get(cod).getCod())) {
                                    produs.setNumeProdus(numeProdus);
                                }
                            }
                            ListaProduse.produseMap.get(cod).setNumeProdus(numeProdus);
                            System.out.println("Produs actualizat cu succes");
                        }
                    } else if (atribut == 2) {
                        System.out.println("Apasa x pentru a anula");
                        System.out.print("Producator = ");
                        String producator = new Scanner(System.in).nextLine();
                        if (!producator.equals("x")) {
                            for (Produs produs : ListaProduse.getInstance().getProduse()) {
                                if (produs.getCod().equals(ListaProduse.produseMap.get(cod).getCod())) {
                                    produs.setProducator(producator);
                                }
                            }
                            ListaProduse.produseMap.get(cod).setProducator(producator);
                            System.out.println("Produs actualizat cu succes");
                        }
                    } else if (atribut == 3) {
                        for (TipProdus tip : TipProdus.genProdusMap.values()) {
                            System.out.println(tip.name());
                        }
                        System.out.println("Apasa x pentru a anula");
                        System.out.print("Tip produs = ");
                        String tip = new Scanner(System.in).nextLine();
                        if (!tip.equals("x")) {
                            TipProdus tipProdus;
                            while (true) {
                                if (TipProdus.genProdusMap.containsKey(tip)) {
                                    tipProdus = TipProdus.genProdusMap.get(tip);
                                    break;
                                } else {
                                    System.out.println("----------------Valoare incorecta!----------------");
                                    System.out.println("Introdu o noua valoare");
                                    tip = new Scanner(System.in).nextLine();
                                }
                            }
                            for (Produs produs : ListaProduse.getInstance().getProduse()) {
                                if (produs.getCod().equals(ListaProduse.produseMap.get(cod).getCod())) {
                                    produs.setTipProdus(tipProdus);
                                }
                            }
                            ListaProduse.produseMap.get(cod).setTipProdus(tipProdus);
                            System.out.println("Produs actualizat cu succes");
                        }
                    } else if (atribut == 4) {
                        System.out.println("Apasa 0 pentru a anula");
                        System.out.print("Garantie = ");
                        int garantie;
                        while (true) {
                            try {
                                garantie = new Scanner(System.in).nextInt();
                                if (garantie == 0) {
                                    break;
                                }
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("----------------Valoare incorecta! Introdu un numar!----------------");
                            }
                        }
                        if (garantie != 0) {
                            for (Produs produs : ListaProduse.getInstance().getProduse()) {
                                if (produs.getCod().equals(ListaProduse.produseMap.get(cod).getCod())) {
                                    produs.setGarantie(garantie);
                                }
                            }
                            ListaProduse.produseMap.get(cod).setGarantie(garantie);
                            System.out.println("Produs actualizat cu succes");
                        }
                    } else if (atribut == 5) {
                        System.out.println("Apasa 0 pentru a anula");
                        System.out.print("Stoc = ");
                        int stoc;
                        while (true) {
                            try {
                                stoc = new Scanner(System.in).nextInt();
                                if (stoc == 0) {
                                    break;
                                }
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("----------------Valoare incorecta! Introdu un numar!----------------");
                            }
                        }
                        if (stoc != 0) {
                            for (Produs produs : ListaProduse.getInstance().getProduse()) {
                                if (produs.getCod().equals(ListaProduse.produseMap.get(cod).getCod())) {
                                    produs.setStoc(stoc);
                                }
                            }
                            ListaProduse.produseMap.get(cod).setStoc(stoc);
                            System.out.println("Produs actualizat cu succes");
                        }
                    } else {
                        System.out.println("Comanda incorecta");
                    }
                }
            }
        }
    }

    public static void stergeProdus() {
        System.out.println("Apasa 0 pentru a anula");
        if (ListaProduse.getInstance().getProduse().isEmpty()) {
            System.out.println("Nu exista produse");
        } else {
            System.out.print("Codul produsului = ");
            int cod;
            while (true) {
                try {
                    cod = new Scanner(System.in).nextInt();
                    if (cod < 0 || cod > entryList.get(entryList.size() - 1).getKey()) {
                        throw new InvalidValueException("Numar incorect");
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("----------------Valoare incorecta! Introdu un numar!----------------");
                } catch (InvalidValueException e) {
                    System.out.println("Introdu un cod care se afla in lista");
                }
            }
            if (cod != 0) {
                ListaProduse.getInstance().getProduse().remove(ListaProduse.produseMap.get(cod));
                ListaProduse.produseMap.remove(cod);
                System.out.println("Produsul a fost stears cu succes");
            }
        }
    }

    public static void raportProduseProducator() throws IOException {
        List<Produs> produseProducator = new LinkedList<>();
        System.out.print("Producator = ");
        String producatorProdus = new Scanner(System.in).nextLine();
        if (ListaProduse.getInstance().getProduse().isEmpty()) {
            System.out.println("Nu exista produse");
        } else {
            for (Produs produs : ListaProduse.getInstance().getProduse()) {
                if (produs.getProducator().equals(producatorProdus)) {
                    produseProducator.add(produs);
                }
            }
            if (produseProducator.isEmpty()) {
                System.out.println("Nu exista produse facute de acest producator");
            } else {
                for (Produs produs : produseProducator) {
                    System.out.println(produs.toString());
                }
                System.out.println("Vrei sa exporti acest raport?");
                System.out.println("1 - Da 1");
                System.out.println("0 - Nu 0");
                int alegere;
                while (true) {
                    while (true) {
                        try {
                            alegere = new Scanner(System.in).nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("----------------Valoare incorecta! Apasa 1 sau 0!----------------");
                        }
                    }
                    if (alegere == 1) {
                        Fisier fisier = new Fisier("produse" + produseProducator.get(0).getProducator() + ".txt");
                        fisier.genereazaFisierRaport((LinkedList<Produs>) produseProducator);
                        System.out.println("Raport generat");
                        break;
                    } else if (alegere == 0) {
                        break;
                    } else {
                        System.out.println("----------------Comanda inexistenta! Apasa 1 sau 0!----------------");
                    }
                }
            }
        }
    }

    public static void raportProduseTip() throws IOException {
        List<Produs> produseTip = new LinkedList<>();
        System.out.print("Tip produs = ");
        String tip = new Scanner(System.in).nextLine();
        TipProdus tipProdus;
        while (true) {
            if (TipProdus.genProdusMap.containsKey(tip)) {
                tipProdus = TipProdus.genProdusMap.get(tip);
                break;
            } else {
                System.out.println("----------------Valoare incorecta!----------------");
                System.out.println("Introdu o noua valoare");
                tip = new Scanner(System.in).nextLine();
            }
        }
        if (ListaProduse.getInstance().getProduse().isEmpty()) {
            System.out.println("Nu exista produse");
        } else {
            for (Produs produs : ListaProduse.getInstance().getProduse()) {
                if (produs.getTipProdus().equals(tipProdus)) {
                    produseTip.add(produs);
                }
            }
            if (produseTip.isEmpty()) {
                System.out.println("Nu exista produse de acest tip");
            } else {
                for (Produs produs : produseTip) {
                    System.out.println(produs.toString());
                }
                System.out.println("Vrei sa exporti acest raport?");
                System.out.println("1 - Da");
                System.out.println("0 - Nu");
                int alegere;
                while (true) {
                    while (true) {
                        try {
                            alegere = new Scanner(System.in).nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("----------------Valoare incorecta! Apasa 1 sau 0!----------------");
                        }
                    }
                    if (alegere == 1) {
                        Fisier fisier = new Fisier("produse" + produseTip.get(0).getTipProdus() + ".txt");
                        fisier.genereazaFisierRaport((LinkedList<Produs>) produseTip);
                        System.out.println("Raport generat");
                        break;
                    } else if (alegere == 0) {
                        break;
                    } else {
                        System.out.println("----------------Comanda inexistenta! Apasa 1 sau 0!----------------");
                    }
                }
            }
        }
    }


}
