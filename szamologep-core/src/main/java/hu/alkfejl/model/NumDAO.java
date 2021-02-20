package hu.alkfejl.model;

import hu.alkfejl.model.bean.Num;

import java.util.List;

public interface NumDAO {

    boolean addNum(Num n);
    List<Num> listNum();
}
