package com.example.lisansmastercalisma;

import java.io.Serializable;

public class ogrenciler implements Serializable {

    private String ad;
    private String no;
    private String lisanstipi;
    private String okul;

    public ogrenciler(String ad, String no, String lisanstipi, String okul){
        this.ad = ad;
        this.no = no;
        this.lisanstipi = lisanstipi;
        this.okul = okul;
    }

    public String getAd() {
        return ad;
    }

    public String getNo() {
        return no;
    }

    public String getLisanstipi() {
        return lisanstipi;
    }

    public String getOkul() {
        return okul;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setLisanstipi(String lisanstipi) {
        this.lisanstipi = lisanstipi;
    }

    public void setOkul(String okul) {
        this.okul = okul;
    }

    public String String(){
        return ad + " " + no  + " " + okul;
    }
}
