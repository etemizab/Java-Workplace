package ch.zhaw.projekt;

import javax.imageio.ImageIO;
import java.io.File;

// Da im Kaggle Datensatz viele fehlerhafte/beschädigte Bilder waren
// und die training.java immer wieder abgebrochen ist, wurde diese Klasse
// erstellt, um alle Bilder zu löschen, die nicht geöffnet werden konnten.

public class CleanDataset {

    public static void main(String[] args) {
        File dir = new File("PetImages");
        cleanFolder(dir);
    }

    public static void cleanFolder(File folder) {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                cleanFolder(file);
            } else {
                try {
                    if (ImageIO.read(file) == null) {
                        System.out.println("Lösche defektes Bild: " + file.getAbsolutePath());
                        // file.delete();
                    }
                } catch (Exception e) {
                    System.out.println("Lösche fehlerhaftes Bild: " + file.getAbsolutePath());
                    // file.delete();
                }
            }
        }
    }
}