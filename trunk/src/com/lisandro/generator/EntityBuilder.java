package com.lisandro.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.joda.time.LocalDate;

import com.lisandro.generator.util.Constants;
import com.lisandro.generator.util.Utils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

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
    private static final String[] excludeColumns=new String[]{
        "USUARIOCREAR","FECHACREAR","USUARIOMODIFICAR","FECHAMODIFICAR","USUARIOBORRAR","FECHABORRAR",
        "BORRADO","VERSION"
    };

    Configuration templateConfig=new Configuration();
    
    public EntityBuilder(String tableName){
        this.class_name=Utils.tableNameToClassName(tableName);
        entityModel.setTableName(tableName);
        entityModel.setClassName(class_name);
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
        
        dir = new File(baseDir.getAbsolutePath() + "/service");
        dir.mkdirs();
        MANAGER_URL = dir.getAbsolutePath();
        dir = new File(baseDir.getAbsolutePath() + "/service/impl");
        dir.mkdirs();
    }
    public void createModel(ResultSet rsColumns) {
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
                    fieldClass=LocalDate.class;
                }
                if(fieldClass==null)
                    continue;
                boolean pasar=false;
                for(String col:excludeColumns){
                    if(col.equalsIgnoreCase(nombreColumna)){
                        pasar=true;
                        break;
                    }
                        
                }
                if(pasar)
                    continue;
                entityAttribute.setFieldClass(fieldClass);
                entityAttribute.setFieldName(nombreColumna.toLowerCase());
                entityAttribute.setTableFieldName(nombreColumna);
                entityModel.getAtributos().add(entityAttribute);
             }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
    public void createMainClass() {
        
        try {
            templateConfig.setDirectoryForTemplateLoading(
                    new File("./bin/resources/templates"));
            templateConfig.setObjectWrapper(new DefaultObjectWrapper());
            /* Get or create a template */
            Template template = templateConfig.getTemplate("clase.ftl");

            PrintWriter pw= new PrintWriter(CLASS_URL+"/"+class_name+".java");
            template.process(entityModel,pw);
            pw.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TemplateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    
    public void createManager(){
        try {
            templateConfig.setDirectoryForTemplateLoading(
                    new File("./bin/resources/templates"));
            System.out.println(new File("./").getAbsolutePath());
            templateConfig.setObjectWrapper(new DefaultObjectWrapper());
            /* Get or create a template */
            Template templateManager = templateConfig.getTemplate("manager.ftl");

            PrintWriter pwManager= new PrintWriter(MANAGER_URL+"/"+class_name+"Manager.java");
            templateManager.process(entityModel,pwManager);
            pwManager.flush();
            
            Template templateManagerDefault = templateConfig.getTemplate("managerDefault.ftl");
            PrintWriter pwManagerDefault= new PrintWriter(MANAGER_URL+"/impl/"+class_name+"ManagerDefault.java");
            templateManagerDefault.process(entityModel,pwManagerDefault);
            pwManagerDefault.flush();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TemplateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void createDto(){
        
    }
    public void createDao(){
        
    }
}
