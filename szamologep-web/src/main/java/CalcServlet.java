import hu.alkfejl.model.CalcDAODBImp;
import hu.alkfejl.model.Calculator;
import hu.alkfejl.model.NumDAOImp;
import hu.alkfejl.model.bean.Calc;
import hu.alkfejl.model.bean.Num;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@WebServlet("/Calculator")
public class CalcServlet extends HttpServlet {
    private Calculator calc = new Calculator();
    private CalcDAODBImp db = new CalcDAODBImp();
    private NumDAOImp ndb = new NumDAOImp();
    private int dbIter = 0;
    private int ndbIter = 0;
    private boolean isFirstNum = true;
    private String num1 = "";
    private String num2 = "";
    private String sign = "+";


    /**
     * Gomb lenyomasonkent a megfelelo muveleteket hajtja vegre
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("select1") != null){   // szam szelektor
            this.isFirstNum = true;
        } else if (req.getParameter("select2") != null){
            this.isFirstNum = false;
        } else if (req.getParameter("7") != null){     // elso sor
            addString("7");
        } else if (req.getParameter("8") != null){
            addString("8");
        } else if (req.getParameter("9") != null){
            addString("9");
        } else if (req.getParameter("sin") != null){
            calculate(calc.sin(toNum(num1)));
        } else if (req.getParameter("+") != null){
            changeSign("+");
        } else if (req.getParameter("%") != null){
            changeSign("%");
        } else if (req.getParameter("M+") != null){
            addCalc();
        } else if (req.getParameter("4") != null){  // masodik sor
            addString("4");
        } else if (req.getParameter("5") != null){
            addString("5");
        } else if (req.getParameter("6") != null){
            addString("6");
        } else if (req.getParameter("cos") != null){
            calculate(calc.cos(toNum(num1)));
        } else if (req.getParameter("-") != null){
            changeSign("-");
        } else if (req.getParameter("square") != null){
            calculate(calc.mult(toNum(num1),toNum(num1)));
        } else if (req.getParameter("M") != null){
            getNextCalc();
        } else if (req.getParameter("1") != null){  // harmadik sor
            addString("1");
        } else if (req.getParameter("2") != null){
            addString("2");
        } else if (req.getParameter("3") != null){
            addString("3");
        } else if (req.getParameter("tan") != null){
            calculate(calc.tan(toNum(num1)));
        } else if (req.getParameter("*") != null){
            changeSign("*");
        } else if (req.getParameter("squareR") != null){
            calculate(calc.sqrt(toNum(num1)));
        } else if (req.getParameter("m+") != null){
            addNum();
        } else if (req.getParameter("sign") != null){    // negyedik sor
            swapPrefix();
        } else if (req.getParameter("0") != null){
            addString("0");
        } else if (req.getParameter(".") != null){
            newPoint();
        } else if (req.getParameter("del") != null){
            del();
        } else if (req.getParameter("/") != null){
            changeSign("/");
        } else if (req.getParameter("rec") != null){
            calculate(calc.div(toNum("1"), toNum(num1)));
        } else if (req.getParameter("m") != null){
            getNextNum();
        } else if (req.getParameter("=") != null){
            if (sign == "+") {
                calculate(calc.add(toNum(num1), toNum(num2)));
            } else if (sign == "-") {
                calculate(calc.sub(toNum(num1), toNum(num2)));
            } else if (sign == "*") {
                calculate(calc.mult(toNum(num1), toNum(num2)));
            } else if (sign == "/") {
                calculate(calc.div(toNum(num1), toNum(num2)));
            } else if (sign == "%") {
                calculate(calc.divRem(toNum(num1), toNum(num2)));
            }
        }

        resp.sendRedirect("pages/calculator.jsp");
    }

    /**
     * Attributum listaba menti a kiirando adatokat
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("number1", this.num1);
        req.setAttribute("number2", this.num2);
        req.setAttribute("sign", this.sign);
    }

    /**
     * Helper muvelethez
     * @param result
     */
    private void calculate(Double result) {
        num1 = String.format(Locale.US,"%f", result);
        num2 = "";
        sign = "+";
    }

    /**
     * Helper muveleti jel valtashoz
     * @param s
     */
    private void changeSign(String s){
        sign = s;
    }

    /**
     * A szam vegere ir egy karaktert
     * @param s
     */
    private void addString(String s){
        if (isFirstNum) {
            this.num1 += s;
        } else {
            this.num2 += s;
        }
    }

    /**
     * Pont torlese a stringbol vegere rakunk egy ujat
     */
    private void newPoint(){
        if (isFirstNum) {
            num1 = num1.replace(".", "");
        } else {
            num2 = num2.replace(".", "");
        }
        addString(".");
    }

    /**
     * Elojel valtas
     */
    private void swapPrefix(){
        if (isFirstNum) {
            if (num1.startsWith("-")) {
                num1 = num1.substring(1);
            } else {
                num1 = "-" + num1;
            }
        } else {
            if (num2.startsWith("-")) {
                num2 = num2.substring(1);
            } else {
                num2 = "-" + num2;
            }
        }
    }

    /**
     * Utolso char torlese
     */
    private void del() {
        if (isFirstNum && num1.length() > 0) {
            num1 = num1.substring(0, num1.length() - 1);
        } else if (num2.length() > 0){
            num2 = num2.substring(0, num2.length() - 1);
        }
    }

    /**
     * Muvelet mentese
     */
    private void addCalc() {
        Calc c = new Calc();

        try {
            c.setNum1(num1);
            c.setNum2(num2);
            c.setSign(sign);

            db.addCalc(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Szam mentese
     */
    private void addNum() {
        Num num = new Num();

        try {
            num.setNumber(this.num1);

            ndb.addNum(num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Muveletek listazasa
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
    }

    /**
     * Szamok listazasa
     */
    private void getNextNum() {
        List<Num> list = ndb.listNum();

        if (list.isEmpty()) {
            return;
        } else if (list.size() == this.ndbIter){
            this.ndbIter = 0;
            this.num1 = list.get(0).getNumber();
        } else {
            this.num1 = list.get(this.ndbIter).getNumber();
            this.ndbIter += 1;
        }
    }

    /**
     * Helper a muvelet betoltesehez
     * @param c
     */
    private void setValues(Calc c){
        this.num1 = c.getNum1();
        this.num2 = c.getNum2();
        this.sign = c.getSign();
    }

    /**
     * Helper String szamma alakitasa
     * @param number
     * @return
     */
    private double toNum(String number) {

        if (number == ""){
            return Double.parseDouble("0");
        } else {
            return Double.parseDouble(number);
        }
    }
}
