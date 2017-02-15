package by.training.rest.model;

public class ColorShoes {

    private int id;
    private int idColor;
    private int idShoes;
    private int amount;

    public ColorShoes(){}

    public ColorShoes(int id, int idColor, int idShoes, int amount) {
        this.id = id;
        this.idColor = idColor;
        this.idShoes = idShoes;
        this.amount = amount;
    }

    public ColorShoes(int idColor, int idShoes, int amount) {
        this.idColor = idColor;
        this.idShoes = idShoes;
        this.amount = amount;
    }

    public static boolean isEmptyFields(ColorShoes entity){
        if(entity==null){
            return true;
        }if(entity.getIdShoes()<1){
            return true;
        }if(entity.getIdColor()<1)
            return true;
        return false;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdColor() {
        return idColor;
    }

    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }

    public int getIdShoes() {
        return idShoes;
    }

    public void setIdShoes(int idShoes) {
        this.idShoes = idShoes;
    }

}
