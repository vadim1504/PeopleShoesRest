package by.training.rest.model;

public class SizeShoes {

    private int id;
    private int idSize;
    private int idShoes;
    private int amount;

    public SizeShoes(){}

    public SizeShoes(int id, int idSize, int idShoes, int amount) {
        this.id = id;
        this.idSize = idSize;
        this.idShoes = idShoes;
        this.amount = amount;
    }

    public SizeShoes(int idSize, int idShoes, int amount) {
        this.idSize = idSize;
        this.idShoes = idShoes;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSize() {
        return idSize;
    }

    public void setIdSize(int idSize) {
        this.idSize = idSize;
    }

    public int getIdShoes() {
        return idShoes;
    }

    public void setIdShoes(int idShoes) {
        this.idShoes = idShoes;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }



}
