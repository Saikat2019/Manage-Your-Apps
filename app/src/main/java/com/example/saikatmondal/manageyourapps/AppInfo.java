package com.example.saikatmondal.manageyourapps;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class AppInfo implements Serializable {  //TODO : know more about serializable

    private String name;
    private String apk;
    private String version;
    private String source;
    private String data;
    private Drawable icon;
    private Boolean system; //boolean is either true or false but Boolean can be true ,false and null
                            //Boolean is a object wrapper for Boolean,There is seldom a reason to use
    // a Boolean over a boolean except in cases when an object reference is required, such as in a List
    //To know more about this

    public AppInfo(String name,String apk,String version,String source,String data,Drawable icon,Boolean system) {
        this.name = name;
        this.apk = apk;
        this.version = version;
        this.source = source;
        this.data = data;
        this.icon = icon;
        this.system = system;
    }
    //A class can have multiple constructors, as long as their signature (the parameters they take) are not the same
    //This is called constructor overloading

    public AppInfo(String string) {
        String[] split = string.split("##");
        if (split.length == 6) {
            this.name = split[0];
            this.apk = split[1];
            this.version = split[2];
            this.source = split[3];
            this.data = split[4];
            this.system = Boolean.getBoolean(split[5]);
        }
    }

    public String getName(){return name;}
    public String getApk(){return apk;}
    public String getVersion(){return version;}
    public String getSource(){return source;}
    public String getData(){return data;}
    public Drawable getIcon(){return icon;}
    public void setIcon(){this.icon = icon;}
    public Boolean isSystem(){return system;}

    public String toString(){
        return getName()+"##"+getApk()+"##"+getVersion()+"##"+getSource()+"##"+getData()+"##"+isSystem();
    }

}


