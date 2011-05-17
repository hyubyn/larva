package test;

import java.sql.SQLException;

import org.junit.Test;

import com.lisandro.generator.Generator;

public class GeneratorTest {
    @Test
    public void testGenerator(){
        Generator g=new Generator();
        try {
            g.generarClases();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
