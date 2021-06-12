package com.example.licentaBackendSB.others;

public class Validator {

    //Methods
    public static Boolean checkCaminSpelling(String caminReceived) {
        return (caminReceived.equals("Leu A"))
                || (caminReceived.equals("Leu C"))
                || (caminReceived.equals("P20"))
                || (caminReceived.equals("P23"));
    }
}
