package by.training.rest.model;

public class Cart {

    private int id;
    private int idUser;
    private int idShoes;

    public Cart(){}

    public Cart(int id, int idUser, int idShoes) {
        this.id = id;
        this.idUser = idUser;
        this.idShoes = idShoes;
    }

    public Cart(int idUser, int idShoes) {
        this.idUser = idUser;
        this.idShoes = idShoes;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdShoes() {
        return idShoes;
    }

    public void setIdShoes(int idShoes) {
        this.idShoes = idShoes;
    }
}
