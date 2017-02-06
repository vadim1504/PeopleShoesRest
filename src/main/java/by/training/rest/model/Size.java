package by.training.rest.model;

import java.util.List;

public class Size {

    private int id;
    private String sizeEU;
    private String sizeUS;
    private String sizeUK;
    private List<Shoes> shoesList;

    public Size(){}

    public Size(int id, String sizeEU, String sizeUS, String sizeUK) {
        this.id = id;
        this.sizeEU = sizeEU;
        this.sizeUS = sizeUS;
        this.sizeUK = sizeUK;
    }

    public Size(String sizeEU, String sizeUS, String sizeUK) {
        this.sizeEU = sizeEU;
        this.sizeUS = sizeUS;
        this.sizeUK = sizeUK;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSizeEU() {
        return sizeEU;
    }

    public void setSizeEU(String sizeEU) {
        this.sizeEU = sizeEU;
    }

    public String getSizeUS() {
        return sizeUS;
    }

    public void setSizeUS(String sizeUS) {
        this.sizeUS = sizeUS;
    }

    public String getSizeUK() {
        return sizeUK;
    }

    public void setSizeUK(String sizeUK) {
        this.sizeUK = sizeUK;
    }

    public List<Shoes> getShoesList() {
        return shoesList;
    }

    public void setShoesList(List<Shoes> shoesList) {
        this.shoesList = shoesList;
    }
}
