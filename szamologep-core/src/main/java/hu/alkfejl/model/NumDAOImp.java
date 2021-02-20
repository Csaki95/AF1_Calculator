package hu.alkfejl.model;

import hu.alkfejl.model.bean.Calc;
import hu.alkfejl.model.bean.Num;

import java.util.*;

/**
 * Szamok tarolasa memoriaban
 */
public class NumDAOImp implements NumDAO {
    List<Num> list;

    public NumDAOImp(){
        list = new ArrayList<>();
    }

    @Override
    public boolean addNum(Num n) {
        return list.add(n);
    }

    @Override
    public List<Num> listNum() {
        return list;
    }
}
