package models;

import java.io.IOException;
import java.lang.reflect.Type;

public interface FileManager<T> {

    void save(T obj);

    T load(Type typeOfT) throws IOException;

    void deleteSaves();

    boolean fileExist();

}
