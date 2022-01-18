package com.farmerhouse.ansabMazro3at.models;

import java.util.List;

public class Zera3aTypes {


    String tableName;
    String name;
    List<Zera3aTypesChoices> data;
    String resultId = "";
    String resultName = "";


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Zera3aTypesChoices> getData() {
        return data;
    }

    public void setData(List<Zera3aTypesChoices> data) {
        this.data = data;
    }

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    public String getResultName() {
        return resultName;
    }

    public void setResultName(String resultName) {
        this.resultName = resultName;
    }
}
