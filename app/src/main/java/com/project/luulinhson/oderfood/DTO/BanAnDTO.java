package com.project.luulinhson.oderfood.DTO;

/**
 * Created by Admin on 1/17/2017.
 */

public class BanAnDTO {
    int mabanan;
    String tenbanan;
    String trangthaibanan;
    boolean checked = false;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getMabanan() {
        return mabanan;
    }

    public void setMabanan(int mabanan) {
        this.mabanan = mabanan;
    }

    public String getTenbanan() {
        return tenbanan;
    }

    public void setTenbanan(String tenbanan) {
        this.tenbanan = tenbanan;
    }

    public String getTrangthaibanan() {
        return trangthaibanan;
    }

    public void setTrangthaibanan(String trangthaibanan) {
        this.trangthaibanan = trangthaibanan;
    }


}
