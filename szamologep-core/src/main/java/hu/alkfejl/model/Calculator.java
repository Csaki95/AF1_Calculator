package hu.alkfejl.model;

/**
 * Szamologep muveletek kiszervezve
 */
public class Calculator {

    public double add(double a, double b) {
        return a+b;
    }

    public double sub(double a, double b) {
        return a-b;
    }

    public double mult(double a, double b) {
        return a*b;
    }

    public double div(double a, double b) {
        return a/b;
    }

    public double divRem(double a, double b) {
        return a%b;
    }

    public double sqrt(double a) {
        return Math.sqrt(a);
    }

    public double sin(double a) {
        return Math.sin(a);
    }

    public double cos(double a) {
        return Math.cos(a);
    }

    public double tan(double a) {
        return Math.tan(a);
    }
}
