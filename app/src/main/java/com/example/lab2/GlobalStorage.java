package com.example.lab2;

import java.util.List;

public class GlobalStorage {
    private String globalVariableOne;
    private List<String> globalArrayList;

    public String getGlobalVariableOne() {
        return globalVariableOne;
    }

    public void setGlobalVariableOne(String globalVariableOne) {
        this.globalVariableOne = globalVariableOne;
    }

    public List<String> getGlobalArrayList() {
        return globalArrayList;
    }

    public void setGlobalArrayList(List<String> globalArrayList) {
        this.globalArrayList = globalArrayList;
    }


}
