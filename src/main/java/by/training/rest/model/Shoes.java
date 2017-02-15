package by.training.rest.model;

import by.training.rest.model.price.Price;

import java.util.List;

public class Shoes {

    private int id;
    private int idMaterial;
    private Material material;
    private Brand brand;
    private int idBrand;
    private String nameRu;
    private String nameEu;
    private int amount;
    private String image;
    private Price price;
    private List<Color> colorList;
    private List<Size> sizes;
    private List<MenCollection> menCollections;

    public Shoes(){}

    public Shoes(int idMaterial, int idBrand, String nameRu, String nameEu, int amount, String image, Price price) {
        this.idMaterial = idMaterial;
        this.idBrand = idBrand;
        this.nameRu = nameRu;
        this.nameEu = nameEu;
        this.amount = amount;
        this.image = image;
        this.price = price;
    }

    public Shoes(int id, int idMaterial, int idBrand, String nameRu, String nameEu, int amount, String image,Price price) {
        this.id = id;
        this.idMaterial = idMaterial;
        this.idBrand = idBrand;
        this.nameRu = nameRu;
        this.nameEu = nameEu;
        this.amount = amount;
        this.image = image;
        this.price = price;
    }

    public Shoes(int idMaterial, Material material, Brand brand, int idBrand, String nameRu, String nameEu, int amount, String image, Price price, List<Color> colorList, List<Size> sizes, List<MenCollection> menCollections) {
        this.idMaterial = idMaterial;
        this.material = material;
        this.brand = brand;
        this.idBrand = idBrand;
        this.nameRu = nameRu;
        this.nameEu = nameEu;
        this.amount = amount;
        this.image = image;
        this.price = price;
        this.colorList = colorList;
        this.sizes = sizes;
        this.menCollections = menCollections;
    }

    public Shoes(int id, int idMaterial, Material material, Brand brand, int idBrand, String nameRu, String nameEu, int amount, String image, Price price, List<Color> colorList, List<Size> sizes, List<MenCollection> menCollections) {
        this.id = id;
        this.idMaterial = idMaterial;
        this.material = material;
        this.brand = brand;
        this.idBrand = idBrand;
        this.nameRu = nameRu;
        this.nameEu = nameEu;
        this.amount = amount;
        this.image = image;
        this.price = price;
        this.colorList = colorList;
        this.sizes = sizes;
        this.menCollections = menCollections;
    }

    public static boolean isEmptyFields(Shoes entity){
        if(entity==null){
            return true;
        }if(entity.getNameRu()==null){
            return true;
        }if(entity.getNameEu()==null){
            return true;
        }if(entity.getImage()==null) {
            return true;
        }
        return false;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
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

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Color> getColorList() {
        return colorList;
    }

    public void setColorList(List<Color> colorList) {
        this.colorList = colorList;
    }

    public List<MenCollection> getMenCollections() {
        return menCollections;
    }

    public void setMenCollections(List<MenCollection> menCollections) {
        this.menCollections = menCollections;
    }

    public List<Size> getSizes() {
        return sizes;
    }

    public void setSizes(List<Size> sizes) {
        this.sizes = sizes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shoes shoes = (Shoes) o;
        return id == shoes.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Shoes{" +
                "id=" + id +
                ", idMaterial=" + idMaterial +
                ", material=" + material +
                ", brand=" + brand +
                ", idBrand=" + idBrand +
                ", nameRu='" + nameRu + '\'' +
                ", nameEu='" + nameEu + '\'' +
                ", amount=" + amount +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", colorList=" + colorList +
                ", sizes=" + sizes +
                ", menCollections=" + menCollections +
                '}';
    }
}
