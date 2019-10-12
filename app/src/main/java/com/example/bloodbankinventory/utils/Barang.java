package com.example.bloodbankinventory.utils;

public class Barang {
    private String mcoombserum, mnacl, tabung, pipet, hand,masker, gelas;

    public Barang(String coombserum, String nacl) {
        this.mcoombserum = coombserum;
        this.mnacl = nacl;
    }

    public String getCoombserum() {
        return mcoombserum;
    }

    public void setMcoombserum(String mcoombserum) {
        this.mcoombserum = mcoombserum;
    }

    public void setMnacl(String mnacl) {
        this.mnacl = mnacl;
    }

    public String getNacl() {
        return mnacl;
    }

}
