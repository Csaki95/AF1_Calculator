package hu.alkfejl.model;

import hu.alkfejl.model.bean.Calc;

import java.util.List;

public interface CalcDAO {

    boolean addCalc(Calc c);
    List<Calc> listCalc();
}
