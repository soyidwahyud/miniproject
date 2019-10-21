package com.example.bloodbankinventory.utils;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class Barang implements Parcelable {
    private int mcoombserum, mnacl, tabung, pipet, hand,masker, gelas;

    public Barang(int mcoombserum, int mnacl, int tabung, int pipet, int hand, int masker, int gelas) {
        this.mcoombserum = mcoombserum;
        this.mnacl = mnacl;
        this.tabung = tabung;
        this.pipet = pipet;
        this.hand = hand;
        this.masker = masker;
        this.gelas = gelas;
    }


    protected Barang(Parcel in) {
        mcoombserum = in.readInt();
        mnacl = in.readInt();
        tabung = in.readInt();
        pipet = in.readInt();
        hand = in.readInt();
        masker = in.readInt();
        gelas = in.readInt();
    }

    public int getMcoombserum() {
        return mcoombserum;
    }

    public void setMcoombserum(int mcoombserum) {
        this.mcoombserum = mcoombserum;
    }

    public int getMnacl() {
        return mnacl;
    }

    public void setMnacl(int mnacl) {
        this.mnacl = mnacl;
    }

    public int getTabung() {
        return tabung;
    }

    public void setTabung(int tabung) {
        this.tabung = tabung;
    }

    public int getPipet() {
        return pipet;
    }

    public void setPipet(int pipet) {
        this.pipet = pipet;
    }

    public int getHand() {
        return hand;
    }

    public void setHand(int hand) {
        this.hand = hand;
    }

    public int getMasker() {
        return masker;
    }

    public void setMasker(int masker) {
        this.masker = masker;
    }

    public int getGelas() {
        return gelas;
    }

    public void setGelas(int gelas) {
        this.gelas = gelas;
    }

    public static final Creator<Barang> CREATOR = new Creator<Barang>() {
        @Override
        public Barang createFromParcel(Parcel in) {
            return new Barang(in);
        }

        @Override
        public Barang[] newArray(int size) {
            return new Barang[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mcoombserum);
        dest.writeInt(mnacl);
        dest.writeInt(tabung);
        dest.writeInt(pipet);
        dest.writeInt(hand);
        dest.writeInt(masker);
        dest.writeInt(gelas);
    }
}
