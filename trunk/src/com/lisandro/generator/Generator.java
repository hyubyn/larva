package com.lisandro.generator;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lisandro.generator.db.DataBase;

public class Generator {
    public void generarClases() throws ClassNotFoundException, SQLException{
        DataBase db=new DataBase();
        ResultSet schema=db.getSchema();
        while (schema.next()) {
            // El contenido de cada columna del ResultSet se puede ver
            // en la API, en el metodo getTables() de DataBaseMetaData.
            // La columna 1 es TABLE_CAT
            // y la 3 es TABLE_NAME
            String schemaName = schema.getString(1);
            String tabla = schema.getString(3);
            EntityBuilder builder=new EntityBuilder(tabla);
            System.out.println("TABLA=" + schemaName + "." + tabla);
            ResultSet rsColumns = db.getMetaData().getColumns(schemaName, null, tabla, null);
            builder.createModel(rsColumns);
            builder.createMainClass();
            builder.createManager();
            while (rsColumns.next()) {
               // El contenido de cada columna del ResultSet se puede ver en
               // la API de java, en el metodo getColumns() de DataBaseMetaData
               // La 4 corresponde al TABLE_NAME
               // y la 6 al TYPE_NAME
               String nombreColumna = rsColumns.getString(4);
               String tipoColumna = rsColumns.getString(6);
               System.out.println("\t COLUMNA, nombre=" + nombreColumna
                  + " tipo = " + tipoColumna);
            }
         }
    }
    
}
