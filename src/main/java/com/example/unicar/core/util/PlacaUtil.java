package com.example.unicar.core.util;

import static com.example.unicar.core.util.IsNullUtil.isNullOrEmpty;

public class PlacaUtil {

    public static boolean isValida(String placa){

        placa = placa.replaceAll("\\W", "");

        if (isNullOrEmpty(placa)) {
            return false;
        }

       if(placa.length() != 7){
           return false;
       }

        return placa.matches("[a-zA-Z]{3}[0-9][a-zA-Z][0-9]{2}|[a-zA-Z]{3}[0-9]{4}");

    }

    public static String formatarPlaca(String placa){

        if(placa.matches("(?i)[A-Z]{3}[0-9]{4}")){
            placa = placa.substring(0, 3) + "-" + placa.substring(3, 7);
        }
        return placa.toUpperCase();
    }

}
