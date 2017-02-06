package by.training.rest.model;

import java.util.List;

public class Material {

    private int id;
    private String nameRu;
    private String nameEu;
    private List<Shoes> shoesList;


    public Material(){}

    public Material(int id, String nameRu, String nameEu) {
        this.id = id;
        this.nameRu = nameRu;
        this.nameEu = nameEu;
    }

    public Material(String nameRu, String nameEu) {
        this.nameRu = nameRu;
        this.nameEu = nameEu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameEu() {
        return nameEu;
    }

    public void setNameEu(String nameEu) {
        this.nameEu = nameEu;
    }

    public List<Shoes> getShoesList() {
        return shoesList;
    }

    public void setShoesList(List<Shoes> shoesList) {
        this.shoesList = shoesList;
    }
}
