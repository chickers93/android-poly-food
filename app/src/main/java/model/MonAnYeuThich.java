package model;

import java.io.Serializable;

public class MonAnYeuThich implements Serializable {
    private int soluot;

    public MonAnYeuThich(int soluot) {
        this.soluot = soluot;
    }

    public MonAnYeuThich() {
    }

    public int getSoluot() {
        return soluot;
    }

    public void setSoluot(int soluot) {
        this.soluot = soluot;
    }
}
