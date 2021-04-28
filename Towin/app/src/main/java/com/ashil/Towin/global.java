package com.ashil.Towin;

import android.app.Application;

public class global extends Application {

    int coin;
    String a;

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
