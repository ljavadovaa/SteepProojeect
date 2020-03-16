package app.dao;

import java.util.List;

public interface DAO<A> {
    A getID(int id);
    List<A> getAll();
}
