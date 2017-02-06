package by.training.rest.model;

import java.util.List;

public class Brand {

    private int id;
    private String name;
    private String infoRu;
    private String logo;
    private List<Shoes> shoesList;
    private String infoEu;

    public Brand(){}

    public Brand(int id,String name, String infoRu, String infoEu, String logo) {
        this.id=id;
        this.name = name;
        this.infoRu = infoRu;
        this.infoEu = infoEu;
        this.logo=logo;
    }

    public Brand(String name, String infoRu, String infoEu,String logo) {
        this.name = name;
        this.infoRu = infoRu;
        this.infoEu = infoEu;
        this.logo=logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Shoes> getShoesList() {
        return shoesList;
    }

    public void setShoesList(List<Shoes> shoesList) {
        this.shoesList = shoesList;
    }

}
