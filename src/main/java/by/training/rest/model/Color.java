package by.training.rest.model;

import java.util.List;

public class Color {

    private int id;
    private String nameRu;
    private String nameEu;
    private String image;
    private List<Shoes> shoesList;

    public Color(){}

    public Color(int id, String nameRu, String nameEu, String image) {
        this.id = id;
        this.nameRu = nameRu;
        this.nameEu = nameEu;
        this.image = image;
    }

    public Color(String nameRu, String nameEu, String image) {
        this.nameRu = nameRu;
        this.nameEu = nameEu;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Shoes> getShoesList() {
        return shoesList;
    }

    public void setShoesList(List<Shoes> shoesList) {
        this.shoesList = shoesList;
    }

}
