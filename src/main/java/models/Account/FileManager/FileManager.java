package models.Account.FileManager;

import java.io.IOException;
import java.lang.reflect.Type;

public interface FileManager<T> {

    /**
     * Save the {@code T obj} on file as Json.
     * @param obj   {@code obj} to save on file.
     */
    void save(T obj);

    /**
     * Load a {@code typeOfT obj} from file and return it.
     * @param typeOfT       The type of {@code obj} to be loaded.
     * @return              The {@code obj} loaded from file.
     * @throws IOException  Throw IOExeption if file doesn't exist
     */
    T load(Type typeOfT) throws IOException;

    /**
     * Delete file.
     */
    void deleteSaves();

    /**
     * Return if file exist.
     * @return  {@code Boolean} for file exist.
     */
    boolean fileExist();

}
