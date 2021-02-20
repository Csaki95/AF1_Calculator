package hu.alkfejl.view.controller;

import hu.alkfejl.model.CalcDAODBImp;
import hu.alkfejl.model.Calculator;
import hu.alkfejl.model.NumDAOImp;
import hu.alkfejl.model.bean.Calc;
import hu.alkfejl.model.bean.Num;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CalculatorController {
    /**
     * FXML import
     * minden gombra, es mezore
     * Kevesebb is eleg lett volna, de itt vannak "kesobbi bovithetoseg miatt"
     */
    @FXML
    Button select1;
    @FXML
    Button select2;
    @FXML
    TextField num1;
    @FXML
    TextField num2;
    @FXML
    Text sign;
    @FXML
    Button b9;
    @FXML
    Button b8;
    @FXML
    Button b7;
    @FXML
    Button b6;
    @FXML
    Button b5;
    @FXML
    Button b4;
    @FXML
    Button b3;
    @FXML
    Button b2;
    @FXML
    Button b1;
    @FXML
    Button b0;
    @FXML
    Button pre;
    @FXML
    Button dot;
    @FXML
    Button equal;
    @FXML
    Button sin;
    @FXML
    Button cos;
    @FXML
    Button tan;
    @FXML
    Button del;
    @FXML
    Button add;
    @FXML
    Button sub;
    @FXML
    Button mult;
    @FXML
    Button div;
    @FXML
    Button divRes;
    @FXML
    Button square;
    @FXML
    Button squareR;
    @FXML
    Button rec;
    @FXML
    Button calMp;
    @FXML
    Button calM;
    @FXML
    Button numMp;
    @FXML
    Button numM;
    @FXML
    Button toExchange;

    /**
     * - core modulok
     * - adatbazis iteralasahoz segedvaltozo
     * - melyik szamot irjuk eppen
     * - kiirando szovegek
     */
    private Calculator calc = new Calculator();
    private CalcDAODBImp db = new CalcDAODBImp();
    private NumDAOImp ndb = new NumDAOImp();
    private int dbIter = 0;
    private int ndbIter = 0;
    private boolean isFirstNum = true;
    private String number1 = "";
    private String number2 = "";
    private String calSign = "+";

    public CalculatorController(){
    };

    /**
     * Scene valtasa
     *
     */
    @FXML
    private void swapScene(javafx.event.ActionEvent event) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("/hu/alkfejl/view/valto.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void setFirstNum() { isFirstNum = true; }
    @FXML
    private void setSecondNum() { isFirstNum = false; }
    @FXML
    private void set7() { addString("7"); }
    @FXML
    private void set8() { addString("8"); }
    @FXML
    private void set9() { addString("9"); }
    @FXML
    private void set4() { addString("4"); }
    @FXML
    private void set5() { addString("5"); }
    @FXML
    private void set6() { addString("6"); }
    @FXML
    private void set1() { addString("1"); }
    @FXML
    private void set2() { addString("2"); }
    @FXML
    private void set3() { addString("3"); }
    @FXML
    private void set0() { addString("0"); }
    @FXML
    private void setPrefix() { swapPrefix(); }
    @FXML
    private void delChar() { del(); }
    @FXML
    private void setPoint() { newPoint(); }
    @FXML
    private void calcRes() {
        if (calSign == "+") {
            calculate(calc.add(toNum(number1), toNum(number2)));
        } else if (calSign == "-") {
            calculate(calc.sub(toNum(number1), toNum(number2)));
        } else if (calSign == "*") {
            calculate(calc.mult(toNum(number1), toNum(number2)));
        } else if (calSign == "/") {
            calculate(calc.div(toNum(number1), toNum(number2)));
        } else if (calSign == "%") {
            calculate(calc.divRem(toNum(number1), toNum(number2)));
        }
    }
    @FXML
    private void calcSin() { calculate(calc.sin(toNum(number1))); }
    @FXML
    private void calcCos() { calculate(calc.cos(toNum(number1))); }
    @FXML
    private void calcTan() { calculate(calc.tan(toNum(number1))); }
    @FXML
    private void calcAdd() { changeSign("+"); }
    @FXML
    private void calcSub() { changeSign("-"); }
    @FXML
    private void calcMult() { changeSign("*"); }
    @FXML
    private void calcDiv() { changeSign("/"); }
    @FXML
    private void calcDivRes() { changeSign("%"); }
    @FXML
    private void calcSquare() { calculate(calc.mult(toNum(number1), toNum(number1))); }
    @FXML
    private void calcSquareR() { calculate(calc.sqrt(toNum(number1))); }
    @FXML
    private void calcRec() { calculate(calc.div(toNum("1"), toNum(number1))); }
    @FXML
    private void saveCalMp() { addCalc(); }
    @FXML
    private void saveCalM() { getNextCalc(); }
    @FXML
    private void saveNumMp() { addNum(); }
    @FXML
    private void saveNumM() { getNextNum(); }
    @FXML



    /**
     * Helper szamolas funkciohoz
     * @param result
     */
    private void calculate(Double result) {
        number1 = String.format(Locale.US,"%f", result);
        number2 = "";
        calSign = "+";
        refreshValues();
    }

    /**
     * Helper muveleti jel valtasahoz
     * @param s
     */
    private void changeSign(String s){
        calSign = s;
        refreshValues();
    }

    /**
     * Aktualis szam stringhez hozzaad egy jelet
     * @param s
     */
    private void addString(String s){
        if (isFirstNum) {
            this.number1 += s;
        } else {
            this.number2 += s;
        }
        refreshValues();
    }

    /**
     * A szambol torli a korabbi .-ot, majd a szam vegere helyez egyet
     */
    private void newPoint(){
        if (isFirstNum) {
            number1 = number1.replace(".", "");
        } else {
            number2 = number2.replace(".", "");
        }
        addString(".");
        refreshValues();
    }

    /**
     * Helper elojelet valt
     */
    private void swapPrefix(){
        if (isFirstNum) {
            if (number1.startsWith("-")) {
                number1 = number1.substring(1);
            } else {
                number1 = "-" + number1;
            }
        } else {
            if (number2.startsWith("-")) {
                number2 = number2.substring(1);
            } else {
                number2 = "-" + number2;
            }
        }
        refreshValues();
    }

    /**
     * String vegerol egy char-t torol
     */
    private void del() {
        if (isFirstNum && number1.length() > 0) {
            number1 = number1.substring(0, number1.length() - 1);
        } else if (number2.length() > 0){
            number2 = number2.substring(0, number2.length() - 1);
        }
        refreshValues();
    }

    /**
     * Muveletet ment el adatb-be
     */
    private void addCalc() {
        Calc c = new Calc();

        try {
            c.setNum1(number1);
            c.setNum2(number2);
            c.setSign(calSign);

            db.addCalc(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Szamot ment el memoriaba
     */
    private void addNum() {
        Num num = new Num();

        try {
            num.setNumber(this.number1);

            ndb.addNum(num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Vegig iteral az elmentett muveleteken
     */
    private void getNextCalc() {
        List<Calc> list = db.listCalc();

        if (list.isEmpty()) {
            return;
        } else if (list.size() == this.dbIter) {
            this.dbIter = 0;
            setValues(list.get(0));
        } else {
            setValues(list.get(dbIter));
            this.dbIter += 1;
        }
        refreshValues();
    }

    /**
     * Vegig iteral az elmentett szamokon
     */
    private void getNextNum() {
        List<Num> list = ndb.listNum();

        if (list.isEmpty()) {
            return;
        } else if (list.size() == this.ndbIter){
            this.ndbIter = 0;
            this.number1 = list.get(0).getNumber();
        } else {
            this.number1 = list.get(this.ndbIter).getNumber();
            this.ndbIter += 1;
        }
        refreshValues();
    }

    /**
     * Helper adatbazis betoltes utan aktualizalja az ertekeket
     * @param c
     */
    private void setValues(Calc c){
        this.number1 = c.getNum1();
        this.number2 = c.getNum2();
        this.calSign = c.getSign();
        refreshValues();
    }

    /**
     * Modositas utan frissiti az ertekeket
     */
    private void refreshValues(){
        this.num1.setText(number1);
        this.num2.setText(number2);
        this.sign.setText(calSign);
    }

    private double toNum(String number) {

        if (number == ""){
            return Double.parseDouble("0");
        } else {
            return Double.parseDouble(number);
        }
    }

}
