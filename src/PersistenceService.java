import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Persists and restores system state from a file.
 */
public class PersistenceService {
    public void save(String filePath, SystemState state) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(state);
        }
    }

    public SystemState load(String filePath) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            Object loadedObject = inputStream.readObject();
            return (SystemState) loadedObject;
        } catch (IOException | ClassNotFoundException exception) {
            return null;
        }
    }
}