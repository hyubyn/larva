package test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class DatabaseConnectionTest{
    @Test
    public void conectionTest(){
        try {
//            Class.forName("org.postgresql.Driver");
//            Connection conexion = DriverManager.getConnection(
//               "jdbc:postgresql://10.68.26.130:5432/postgres", "us_catalogo_desa", "us_catalogo_desa");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conexion = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.68.8.84:1521/PFSDESA", "US_CONCERTS_DESA", "US_CONCERTS_DESA");

            DatabaseMetaData metaDatos = conexion.getMetaData();
            /*
             * donde los cuatro par�metros que hemos pasado son:

                - cat�logo de la base de datos. Al poner null, estamos preguntando por el cat�logo actual, que en nuestro ejemplo es de la cadena de conexi�n, "postgres".
                - Esquema de la base de datos. Al poner null, es el actual.
                - Patr�n para las tablas en las que tenemos inter�s. En SQL el caracter que indica "todo" es %, equivalente al * a la hora de listar ficheros. Esto nos dar� todas las tablas del cat�logo y esquema actual. Podr�amos poner cosas como "person%", con lo que obtendr�amos todas las tablas cuyo nombre empiece por "person".
                - El cuarto par�metro es un array de String, en el que pondr�amos qu� tipos de tablas queremos (normales, vistas, etc). Al poner null, nos devolver� todos los tipos de tablas.
             */
            ResultSet rs = metaDatos.getTables(null, "US_CONCERTS_DESA", "TA_%", null);
            
            while (rs.next()) {
                // El contenido de cada columna del ResultSet se puede ver
                // en la API, en el metodo getTables() de DataBaseMetaData.
                // La columna 1 es TABLE_CAT
                // y la 3 es TABLE_NAME
                String catalogo = rs.getString(1);
                String tabla = rs.getString(3);
                System.out.println("TABLA=" + catalogo + "." + tabla);
                ResultSet rsColumns = metaDatos.getColumns(catalogo, null, tabla, null);
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
            
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
