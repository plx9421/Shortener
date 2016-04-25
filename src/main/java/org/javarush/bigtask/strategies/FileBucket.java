package org.javarush.bigtask.strategies;


import org.javarush.bigtask.ExceptionHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Alexey on 18.03.2016.
 */
public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile("fileBucket_", null);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }

        path.toFile().deleteOnExit();
    }

    public long getFileSize() {
        return path.toFile().length();
    }

    public void putEntry(Entry entry) {
        try(ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            oos.writeObject(entry);
//            while (entry.next != null){
//                entry = entry.next;
//                oos.writeObject(entry);
//            }
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }

    }

    public Entry getEntry() {
        Entry result = null;
        if (getFileSize() == 0) return result;

        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))){
            result = (Entry) ois.readObject();
//            Entry et1 = result;
//            while (et1.next != null){
//                Entry et = (Entry) ois.readObject();
//                et1.next = et;
//                et1 = et;
//            }
        } catch (IOException e) {
            ExceptionHandler.log(e);
        } catch (ClassNotFoundException e) {
            ExceptionHandler.log(e);
        }

        return result;
    }

    public void remove() {
        path.toFile().delete();
    }

}
