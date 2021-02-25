package models.Account.FileManager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class FileManagerImp<T> implements FileManager<T> {

    private File file;
    private final Gson gson;

    /**
     * Initialize the {@code file} with an instance of {@code file}.
     * @param file  Instance of {@code file}.
     */
    public FileManagerImp(final File file) {
        this.file = file;
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Initialize the {@code file} with his path.
     * @param path  Path of the file.
     */
    public FileManagerImp(final String path) {
        this.file = new File(path);
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Check if file exist, in case of {@code false} create the file.
     * Save the passed {@code T obj} on the Json file.
     */
    @Override
    public void save(final T obj) {
        try {
            if (!fileExist()) {
                if (!file.createNewFile()) {
                    System.err.println("Failed to create the file");
                    return;
                }
            }
            FileWriter writer = new FileWriter(file);
            gson.toJson(obj, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if file exist, in case of {@code true} try to load the data as {@code typeOfT} from the file
     * and return it as {@code T}.
     * If file exist is {@code false} throw IOExeption.
     */
    @Override
    public T load(final Type typeOfT) throws IOException {
        if (fileExist()) {
            try {
                FileReader reader = new FileReader(file);
                T cards = gson.fromJson(reader, typeOfT);
                reader.close();
                return cards;
            } catch (IOException e) {
                e.printStackTrace();
                throw new IOException();
            }
        } else {
            throw new IOException("File don't exist");
        }
    }

    /**
     * Delete the {@code file} and check if the operation succeeded.
     */
    @Override
    public void deleteSaves() {
        if (!file.delete()) {
            System.err.println("Failed to delete the file");
        }
    }

    /**
     * Return {@code true} if file exist, {@code false} otherwise.
     */
    @Override
    public boolean fileExist() {
        return this.file.exists();
    }

}
