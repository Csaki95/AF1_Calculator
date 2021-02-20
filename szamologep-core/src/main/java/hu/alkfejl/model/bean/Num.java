package hu.alkfejl.model.bean;

/**
 * A memoriaban tarolt szamok bean-je
 */
public class Num {
    private String number;

    public Num(){
    }

    public Num(String a) {
        this.number = a;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
