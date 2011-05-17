package com.lisandro.generator;

public class EntityAttribute {
    private String fieldName;
    private String tableFieldName;
    private Class fieldClass;
    private boolean primaryKey;
    private boolean foreignKey;
    private EntityAttribute foreignClass;
    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public Class getFieldClass() {
        return fieldClass;
    }
    public void setFieldClass(Class fieldClass) {
        this.fieldClass = fieldClass;
    }
    public boolean isPrimaryKey() {
        return primaryKey;
    }
    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }
    public boolean isForeignKey() {
        return foreignKey;
    }
    public void setForeignKey(boolean foreignKey) {
        this.foreignKey = foreignKey;
    }
    public EntityAttribute getForeignClass() {
        return foreignClass;
    }
    public void setForeignClass(EntityAttribute foreignClass) {
        this.foreignClass = foreignClass;
    }
    public String getTableFieldName() {
        return tableFieldName;
    }
    public void setTableFieldName(String tableFieldName) {
        this.tableFieldName = tableFieldName;
    }
    
}
