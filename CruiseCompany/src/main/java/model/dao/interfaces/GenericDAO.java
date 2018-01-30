package model.dao.interfaces;


import java.util.List;

interface GenericDAO<E> extends AutoCloseable {

    int insert(E e);
    void update(E e);
    void delete(int id);
    E findById(int id);
    List<E> findAll();

}
