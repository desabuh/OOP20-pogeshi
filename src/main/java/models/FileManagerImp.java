package models;

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

    public FileManagerImp(final File file) {
        this.file = file;
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public FileManagerImp(final String path) {
        this.file = new File(path);
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

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

    @Override
    public void deleteSaves() {
        if (!file.delete()) {
            System.err.println("Failed to delete the file");
        }
    }

    @Override
    public boolean fileExist() {
        return this.file.exists();
    }

}
