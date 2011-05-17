package com.lisandro.generator;

import java.util.List;

public class EntityModel {
    private String className;
    private List<EntityAttribute> atributos;
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
    
}
