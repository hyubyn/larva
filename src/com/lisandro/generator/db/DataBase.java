package com.lisandro.generator.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBase {
    ResultSet schema;
    DatabaseMetaData metaData;
    public DataBase() throws ClassNotFoundException, SQLException{
        Class.forName("org.postgresql.Driver");
//      Connection conexion = DriverManager.getConnection(
//         "jdbc:postgresql://10.68.26.130:5432/postgres", "us_catalogo_desa", "us_catalogo_desa");
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection conexion = DriverManager.getConnection(
              "jdbc:oracle:thin:@10.68.8.84:1521/PFSDESA", "US_CONCERTS_DESA", "US_CONCERTS_DESA");

      metaData = conexion.getMetaData();
      /*
       * donde los cuatro par�metros que hemos pasado son:

          - cat�logo de la base de datos. Al poner null, estamos preguntando por el cat�logo actual, que en nuestro ejemplo es de la cadena de conexi�n, "postgres".
          - Esquema de la base de datos. Al poner null, es el actual.
          - Patr�n para las tablas en las que tenemos inter�s. En SQL el caracter que indica "todo" es %, equivalente al * a la hora de listar ficheros. Esto nos dar� todas las tablas del cat�logo y esquema actual. Podr�amos poner cosas como "person%", con lo que obtendr�amos todas las tablas cuyo nombre empiece por "person".
          - El cuarto par�metro es un array de String, en el que pondr�amos qu� tipos de tablas queremos (normales, vistas, etc). Al poner null, nos devolver� todos los tipos de tablas.
       */
      schema = metaData.getTables(null, "US_CONCERTS_DESA", "TA_%", null);
    }
    public ResultSet getSchema() {
        return schema;
    }
    public void setSchema(ResultSet schema) {
        this.schema = schema;
    }
    public DatabaseMetaData getMetaData() {
        return metaData;
    }
    public void setMetaData(DatabaseMetaData metaData) {
        this.metaData = metaData;
    }
}
