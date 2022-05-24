package com.example.cryptodo.api.out_models;

import java.lang.reflect.Field;

public class ContractStatusOut {
    public boolean status;
    public String msg;
    public String url;
    public String address;

    public boolean isEmpty()  {

        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (field.get(this)!=null) {
                    return false;
                }
            } catch (Exception e) {
                System.out.println("Exception occured in processing");
            }
        }
        return true;
    }
}
