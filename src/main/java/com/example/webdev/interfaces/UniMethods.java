package com.example.webdev.interfaces;

import java.util.List;

public interface UniMethods<M> {
    void create(M model);
    List<M> readAll();
    M read(int id);
    boolean update(M model, int id);
    boolean delete(int id);

}
