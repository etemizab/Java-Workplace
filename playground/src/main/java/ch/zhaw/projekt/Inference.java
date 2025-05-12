package ch.zhaw.projekt;

import ai.djl.Model;
import ai.djl.ModelException;
import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import ai.djl.modality.cv.transform.Resize;
import ai.djl.modality.cv.transform.ToTensor;
import ai.djl.modality.cv.translator.ImageClassificationTranslator;
import ai.djl.translate.TranslateException;
import ai.djl.translate.Translator;
import ch.zhaw.dto.LabelInfo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.imageio.ImageIO;

/**
 * Service-Klasse für Bildklassifikation mittels DJL.
 */
public class Inference {

    private Predictor<Image, Classifications> predictor;

    // Verzeichnis von Modell-Dateien (.params) und synset.txt
    private static final Path MODEL_DIR = Paths.get("models");

    /**
     * Initialisiert den DJL-Model-Loader mit Translator für Pre-/Post-Processing.
     * Wirft IllegalStateException, wenn Laden fehlschlägt.
     */
    public Inference() {
        try {
            // Das erstellte Modell laden
            Model model = Models.getModel(); // Models.getModel() gibt ein geladenes Modell zurück
            Path modelDir = Paths.get("models");
            model.load(modelDir, Models.MODEL_NAME);
            
            // Translator definieren
            Translator<Image, Classifications> translator = ImageClassificationTranslator.builder()
                    .addTransform(new Resize(Models.IMAGE_WIDTH, Models.IMAGE_HEIGHT))
                    .addTransform(new ToTensor())
                    .optApplySoftmax(true)
                    .build();

            // Predictor erstellen
            predictor = model.newPredictor(translator);

        } catch (Exception e) {
            throw new IllegalStateException("Fehler beim Initialisieren des Modells", e);
        }
    }

    public Classifications predict(byte[] image) throws ModelException, TranslateException, IOException {
        InputStream is = new ByteArrayInputStream(image);
        BufferedImage bi = ImageIO.read(is);
        Image img = ImageFactory.getInstance().fromImage(bi);

        Classifications predictResult = this.predictor.predict(img);
        return predictResult;
    }

    // Listet alle verfügbaren Modelle im MODEL_DIR auf.
   
    public List<String> availableModels() {
        try (Stream<Path> files = Files.list(MODEL_DIR)) {
            return files
                    .filter(p -> p.toString().endsWith(".params"))
                    .map(p -> p.getFileName().toString().replaceFirst("\\.params$", ""))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Auslesen der Modelle", e);
        }
    }

    // Gibt die Labels aus der synset.txt zurück.
    // Diese Methode wird in der GUI verwendet, um die Labels anzuzeigen.
     
    public List<LabelInfo> getLabelInfos() {
        Path synsetFile = MODEL_DIR.resolve("synset.txt");
        try {
            return Files.readAllLines(synsetFile).stream()
                    .map(label -> new LabelInfo(label, "")) // hier kannst du später Beschreibungen ergänzen
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Laden der Labels", e);
        }
    }
}
