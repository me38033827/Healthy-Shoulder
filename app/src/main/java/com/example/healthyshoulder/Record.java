package com.example.healthyshoulder;

/**
 * Created by zh on 2017/3/11.
 */

public class Record {
    private String date;
    private String index1;
    private String index2;
    private String index3;
    private String index4;
    private String index5;
    private String index6;
    private int id;

    public Record(String date, String index1, String index2, String index3, String index4, String index5, String index6, int id) {
        this.date = date;
        this.index1 = index1;
        this.index2 = index2;
        this.index3 = index3;
        this.index4 = index4;
        this.index5 = index5;
        this.index6 = index6;
        this.id = id;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIndex1() {
        return index1;
    }

    public void setIndex1(String index1) {
        this.index1 = index1;
    }

    public String getIndex2() {
        return index2;
    }

    public void setIndex2(String index2) {
        this.index2 = index2;
    }

    public String getIndex3() {
        return index3;
    }

    public void setIndex3(String index3) {
        this.index3 = index3;
    }

    public String getIndex4() {
        return index4;
    }

    public void setIndex4(String index4) {
        this.index4 = index4;
    }

    public String getIndex5() {
        return index5;
    }

    public void setIndex5(String index5) {
        this.index5 = index5;
    }

    public String getIndex6() {
        return index6;
    }

    public void setIndex6(String index6) {
        this.index6 = index6;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
