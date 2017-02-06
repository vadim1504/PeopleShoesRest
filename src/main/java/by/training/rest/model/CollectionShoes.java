package by.training.rest.model;

public class CollectionShoes {

    private int id;
    private int idCollection;
    private int idShoes;

    public CollectionShoes(){}

    public CollectionShoes(int id, int idCollection, int idShoes) {
        this.id = id;
        this.idCollection = idCollection;
        this.idShoes = idShoes;
    }

    public CollectionShoes(int idCollection, int idShoes) {
        this.idCollection = idCollection;
        this.idShoes = idShoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCollection() {
        return idCollection;
    }

    public void setIdCollection(int idCollection) {
        this.idCollection = idCollection;
    }

    public int getIdShoes() {
        return idShoes;
    }

    public void setIdShoes(int idShoes) {
        this.idShoes = idShoes;
    }
}
