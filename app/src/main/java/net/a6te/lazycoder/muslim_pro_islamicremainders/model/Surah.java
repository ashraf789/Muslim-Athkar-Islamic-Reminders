package net.a6te.lazycoder.muslim_pro_islamicremainders.model;

import java.io.Serializable;

public class Surah implements Serializable{
    private String name;
    private long indexNo;
    private String tag;

    public Surah(String name, long indexNo, String tag) {
        this.name = name;
        this.indexNo = indexNo;
        this.tag = tag;
    }

    public Surah(String name, long indexNo) {
        this.name = name;
        this.indexNo = indexNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(long indexNo) {
        this.indexNo = indexNo;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    //    public Surah(String name, long indexNo) {
//        this.name = name;
//        this.indexNo = indexNo;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public long getIndexNo() {
//        return indexNo;
//    }
//
//    public void setIndexNo(long indexNo) {
//        this.indexNo = indexNo;
//    }
}
