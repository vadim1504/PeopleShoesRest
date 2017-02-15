package by.training.rest.model;

public class Cart {

    private int id;
    private String username;
    private int idShoes;

    public Cart(){}

    public Cart(int id, String username, int idShoes) {
        this.id = id;
        this.username = username;
        this.idShoes = idShoes;
    }

    public Cart(String username, int idShoes) {
        this.username = username;
        this.idShoes = idShoes;
    }

    public static boolean isEmptyFields(Cart entity){
        if(entity==null)
            return true;
        if(entity.getUsername()==null)
            return true;
        if(entity.getIdShoes()<1)
            return true;
        return false;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdShoes() {
        return idShoes;
    }

    public void setIdShoes(int idShoes) {
        this.idShoes = idShoes;
    }

    public Shoes getShoes() {
        return shoes;
    }

    public void setShoes(Shoes shoes) {
        this.shoes = shoes;
    }

    private Shoes shoes;
}
