package com.lisandro.generator.util;

public class Utils {
    
    public static String toUpperCamelCase(String texto) {
        char letra = '\0';
        String nuevoTexto = "", resto = "";
        // Borrando los espacios en blanco
        texto = texto.trim();
        // cogiendo un string a partir de la segunda letra
        resto = texto.substring(1).toLowerCase();
        // cogiendo la primera letra en un char
        letra = texto.charAt(0);
        // poniendo dicha letra en mayusculas
        letra = Character.toUpperCase(letra);
        // concatenando todo
        nuevoTexto = letra + resto;
        return nuevoTexto;
    }

    public static String toLowerCamelCase(String texto) {

        char letra = '\0';
        String nuevoTexto = "", resto = "";
        // Borrando los espacios en blanco
        texto = texto.trim();
        // cogiendo un string a partir de la segunda letra
        resto = texto.substring(1);
        // cogiendo la primera letra en un char
        letra = texto.charAt(0);
        // poniendo dicha letra en mayusculas
        letra = Character.toLowerCase(letra);
        // concatenando todo
        nuevoTexto = letra + resto;
        return nuevoTexto;
    }
    public static String tableNameToClassName(String tableName){
        String className="";
        String[] tmp=tableName.split("_");
        
        for(int i=1;i<tmp.length;i++){
            className +=toUpperCamelCase(tmp[i]);
        }
        return className;
    }
}
