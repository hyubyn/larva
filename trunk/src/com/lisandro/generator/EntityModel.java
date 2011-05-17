package com.lisandro.generator;

import java.util.ArrayList;
import java.util.List;

import com.lisandro.generator.util.Utils;

public class EntityModel {
    private String className;    
    private String tableName;
    
    private List<EntityAttribute> atributos=new ArrayList<EntityAttribute>();
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public List<EntityAttribute> getAtributos() {
        return atributos;
    }
    public void setAtributos(List<EntityAttribute> atributos) {
        this.atributos = atributos;
    }
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getUpperClassName(){
        return className.toUpperCase();
    }
    public String getLowerClassName(){
        return className.toLowerCase();
    }
    public String getLowerCamelCaseClassName(){
        return Utils.toLowerCamelCase(className);
    }
    
}
