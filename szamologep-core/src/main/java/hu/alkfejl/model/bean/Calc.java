package hu.alkfejl.model.bean;

/**
 * Muveletek Bean-je
 */
public class Calc {
    private int id;
    private String num1;
    private String num2;
    private String sign;

    public Calc(){
    }

    public Calc(int id, String num1, String num2, String sign){
        this.id = id;
        this.num1 = num1;
        this.num2 = num2;
        this.sign = sign;
    }

    public int getId() { return id; }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
