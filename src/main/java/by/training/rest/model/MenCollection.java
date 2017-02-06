package by.training.rest.model;

import java.util.List;

public class MenCollection {

    private int id;
    private String collectionNameRu;
    private String collectionNameEu;
    private int amount;
    private String image;
    private String infoRu;
    private String infoEu;
    private List<Shoes> shoesList;

    public MenCollection(){}

    public MenCollection(String collectionNameRu, String collectionNameEu, int amount, String image, String infoEu, String infoRu) {
        this.collectionNameRu = collectionNameRu;
        this.collectionNameEu = collectionNameEu;
        this.amount = amount;
        this.image = image;
        this.infoEu = infoEu;
        this.infoRu = infoRu;
    }

    public MenCollection(int id, String collectionNameRu, String collectionNameEu, int amount, String image, String infoEu, String infoRu) {
        this.id = id;
        this.collectionNameRu = collectionNameRu;
        this.collectionNameEu = collectionNameEu;
        this.amount = amount;
        this.image = image;
        this.infoEu = infoEu;
        this.infoRu = infoRu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCollectionNameRu() {
        return collectionNameRu;
    }

    public void setCollectionNameRu(String collectionNameRu) {
        this.collectionNameRu = collectionNameRu;
    }

    public String getCollectionNameEu() {
        return collectionNameEu;
    }

    public void setCollectionNameEu(String collectionNameEu) {
        this.collectionNameEu = collectionNameEu;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInfoRu() {
        return infoRu;
    }

    public void setInfoRu(String infoRu) {
        this.infoRu = infoRu;
    }

    public String getInfoEu() {
        return infoEu;
    }

    public void setInfoEu(String infoEu) {
        this.infoEu = infoEu;
    }

    public List<Shoes> getShoesList() {
        return shoesList;
    }

    public void setShoesList(List<Shoes> shoesList) {
        this.shoesList = shoesList;
    }

}
