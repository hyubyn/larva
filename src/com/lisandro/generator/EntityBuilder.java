package com.lisandro.generator;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lisandro.generator.util.Constants;
import com.lisandro.generator.util.Utils;

public class EntityBuilder {
    private static final String path = "c:/tmp/creator/";
    
    private String MANAGER_URL = "";
    private String DAO_URL = "";
    private String DTO_URL = "";
    private String FLOW_URL = "";
    private String JSP_URL = "";
    private String CLASS_URL = "";
    private String package_url = "";
    private String package_name = "";
    private String class_name = "";
    private String package_base = "";
    private Class clazz = null;
    EntityModel entityModel=new EntityModel();
    
    public EntityBuilder(String tableName){
        this.class_name=Utils.tableNameToClassName(tableName);
        crearEstructuraDirectorio();
    }
    
    private void crearEstructuraDirectorio() {
        File baseDir=new File(path + "/"+Constants.PACKAGE_BASE_NAME+"/"+Constants.PROJECT_NAME +"/"+ Utils.toLowerCamelCase(class_name));
        baseDir.mkdirs();
        
        File dir = new File(baseDir.getAbsolutePath() + "/dao");
        dir.mkdirs();
        DAO_URL = dir.getAbsolutePath();
        dir = new File(baseDir.getAbsolutePath() + "/dao/impl");
        dir.mkdirs();
        dir = new File(baseDir.getAbsolutePath() + "/dto");
        dir.mkdirs();
        DTO_URL = dir.getAbsolutePath();
        dir = new File(baseDir.getAbsolutePath() + "/model");
        dir.mkdirs();
        CLASS_URL = dir.getAbsolutePath();
        dir = new File(path + "/flows/" + Utils.toLowerCamelCase(class_name));
        dir.mkdirs();
        FLOW_URL = dir.getAbsolutePath();
        dir = new File(path + "/jsp/" + Utils.toLowerCamelCase(class_name));
        dir.mkdirs();
        JSP_URL = dir.getAbsolutePath();
        MANAGER_URL = baseDir.getAbsolutePath()+ "/service";
    }

    public void createMainClass(ResultSet rsColumns) {
        
        try {
            while (rsColumns.next()) {
                // El contenido de cada columna del ResultSet se puede ver en
                // la API de java, en el metodo getColumns() de DataBaseMetaData
                // La 4 corresponde al TABLE_NAME
                // y la 6 al TYPE_NAME
                EntityAttribute entityAttribute=new EntityAttribute();
                String nombreColumna = rsColumns.getString(4);
                String tipoColumna = rsColumns.getString(6);
                Class fieldClass=null;
                //NUMBER
                if(tipoColumna.contains("NUMBER")){
                    fieldClass=Long.class;
                }
                //VARCHAR2
                if(tipoColumna.contains("VARCHAR2")){
                    fieldClass=String.class;
                }
                //DATE
                //TIMESTAMP
                if(tipoColumna.contains("DATE")||tipoColumna.contains("TIMESTAMP")){
                    fieldClass=Long.class;
                }
                entityAttribute.setFieldClass(fieldClass);
                entityAttribute.setFieldName(nombreColumna.toLowerCase());
                entityModel.getAtributos().add(entityAttribute);
             }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            new File(CLASS_URL+"/"+class_name+".java").createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
