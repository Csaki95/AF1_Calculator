package hu.alkfejl.model;

import hu.alkfejl.model.bean.Calc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Dao muveletek tarolasahoz
 */
public class CalcDAODBImp implements CalcDAO {

    private static final String CONN = "jdbc:sqlite:calc.db";
    private static final String ADD_CALC = "INSERT INTO calc (num1, num2, sign) VALUES (?,?,?)";
    private static final String LIST_CALC = "SELECT * FROM calc";

    public CalcDAODBImp() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public boolean addCalc(Calc c) {

        try (Connection conn = DriverManager.getConnection(CONN);
             PreparedStatement pst = conn.prepareStatement(ADD_CALC)) {

            pst.setString(1, c.getNum1());
            pst.setString(2, c.getNum2());
            pst.setString(3, c.getSign());

            return pst.executeUpdate() == 1;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Calc> listCalc() {

        List<Calc> calcs = new ArrayList<Calc>();

        try (Connection c = DriverManager.getConnection(CONN);
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(LIST_CALC)) {

            while (rs.next()) {
                calcs.add(new Calc( rs.getInt(1),
                                    rs.getString(2),
                                    rs.getString(3),
                                    rs.getString(4)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return calcs;
    }
}
