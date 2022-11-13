package proiect;

import java.util.HashMap;
import java.util.Map;

public enum TipProdus {
    ELECTRONICE,
    ELECTROCASNICE,
    IGIENA,
    ALIEMENTE,
    MOBILA,
    IMBRACAMINTE,
    INCALTAMINTE,
    ACCESORII,
    JUCARII;

    public static final Map<String, TipProdus> genProdusMap = new HashMap<>();

    static {
        for (TipProdus produs : TipProdus.values())
            genProdusMap.put(produs.toString(), produs);
    }
}

