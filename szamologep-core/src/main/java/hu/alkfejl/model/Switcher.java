package hu.alkfejl.model;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.util.Locale;

public class Switcher {

    /**
     * Szamrendszer valto
     * Stringbol indul ki, ha nem sikerul az atvaltas
     * ervenytelen adatot adnak meg stb. akkor "err"-al ter vissza
     *
     * @param number
     * @param from
     * @param to
     * @return
     */
    public String baseSwitch(String number, String from, String to) {
        return convert(number, baseConversion(from), baseConversion(to));
    }

    private int baseConversion(String string) {
        if (string == null) {
            return 10;
        } else if (string.equals("hex")) {
            return 16;
        } else if (string.equals("dec")) {
            return 10;
        } else if (string.equals("bin")) {
            return 2;
        } else if (string.equals("oct")){
            return 8;
        } else {
            return 10;
        }
    }

    private String convert(String str, int from, int to){
        String result = "";

        try {
            result = Integer.toString(Integer.parseInt(str, from), to);
        } catch (Exception e){
            result = "err";
        }

        return result;
    }

    /**
     * Valuta valto
     *
     * @param value
     * @param fromTo
     * @return
     */
    public String exchange(String value, String fromTo) {
        Double result;

        try {
            if (fromTo.equals("toHuf")){
                result = Double.parseDouble(value) * 330;
            } else {
                result = Double.parseDouble(value) / 330;
            }

            return String.format(Locale.US,"%f", result);
        }catch (Exception e){
            return "err";
        }
    }

    /**
     * Hofok valto
     * magatol ertetodo
     *
     * @param value
     * @param fromTo
     * @return
     */
    public String degree(String value, String fromTo) {
        Double result;

        try {
            if (fromTo.equals("toF")){
                result = ((Double.parseDouble(value) - 32)*5)/9;
            } else {
                result = ((Double.parseDouble(value) * 9)/5)+32;
            }

            return String.format(Locale.US,"%f", result);
        }catch (Exception e){
            return "err";
        }
    }

    /**
     * Suly valto
     * Szebben is ki lehett volna szervezni, de inkabb minden lehetseges opciora
     * legyen egy szorzo
     *
     * @param value
     * @param from
     * @param to
     * @return
     */
    public String weight(String value, String from, String to){
        Double result = null;

        try {
            if (from.equals("mg") && to.equals("mg") ||
                from.equals("g") && to.equals("g") ||
                from.equals("dkg") && to.equals("dkg") ||
                from.equals("kg") && to.equals("kg") ||
                from.equals("t") && to.equals("t")){
                return value;
            } else if (from.equals("mg") && to.equals("g") ||
                       from.equals("g") && to.equals("kg") ||
                       from.equals("kg") && to.equals("t")){
                result = Double.parseDouble(value) / 1000;
            } else if (from.equals("g") && to.equals("dkg")) {
                result = Double.parseDouble(value) / 10;
            } else if (from.equals("dkg") && to.equals("kg")) {
                result = Double.parseDouble(value) / 100;
            } else if (from.equals("mg") && to.equals("dkg")) {
                result = Double.parseDouble(value) / (1000 * 10);
            } else if (from.equals("dkg") && to.equals("t")) {
                result = Double.parseDouble(value) / (1000 * 100);
            } else if (from.equals("mg") && to.equals("kg") ||
                       from.equals("g") && to.equals("t")) {
                result = Double.parseDouble(value) / (1000 * 1000);
            } else if (from.equals("mg") && to.equals("t")) {
                result = Double.parseDouble(value) / (1000 * 1000 * 1000);
            } else if (from.equals("g") && to.equals("mg") ||
                    from.equals("kg") && to.equals("g") ||
                    from.equals("t") && to.equals("kg")){
                result = Double.parseDouble(value) * 1000;
            } else if (from.equals("dkg") && to.equals("g")) {
                result = Double.parseDouble(value) * 10;
            } else if (from.equals("kg") && to.equals("dkg")) {
                result = Double.parseDouble(value) * 100;
            } else if (from.equals("dkg") && to.equals("mg")) {
                result = Double.parseDouble(value) * (1000 * 10);
            } else if (from.equals("t") && to.equals("dkg")) {
                result = Double.parseDouble(value) * (1000 * 100);
            } else if (from.equals("kg") && to.equals("mg") ||
                    from.equals("t") && to.equals("g")) {
                result = Double.parseDouble(value) * (1000 * 1000);
            } else if (from.equals("t") && to.equals("mg")) {
                result = Double.parseDouble(value) * (1000 * 1000 * 1000);
            }
        }catch (Exception e){
            return "err";
        }
        
        return String.format(Locale.US,"%f.8", result);
    }
}
