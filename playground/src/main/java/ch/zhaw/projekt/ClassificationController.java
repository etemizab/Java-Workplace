package ch.zhaw.projekt;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ch.zhaw.dto.LabelInfo;

// Diese Klasse ist der Controller für die REST-API.
// Sie verarbeitet die Anfragen und gibt die Ergebnisse zurück.
// Mit Postman können die Endpunkte getestet werden.

@RestController
public class ClassificationController {

    private final Inference inference = new Inference();

    @GetMapping("/labels")
    public List<LabelInfo> getLabels() {
        return inference.getLabelInfos();
    }

    @GetMapping("/models")
    public List<String> listModels() {
        return inference.availableModels();
    }

    @PostMapping(
      path = "/analyze",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<ClassificationResult> predict(
        @RequestParam("image") MultipartFile image) throws Exception {

        if (image.isEmpty()) {
            throw new IllegalArgumentException("Kein Bild übermittelt.");
        }

        System.out.println("[INFO] Bild empfangen: " + image.getOriginalFilename() +
                " (" + image.getSize() + " Bytes)");

        return inference.predict(image.getBytes())
                .items().stream()
                .map(item -> new ClassificationResult(item.getClassName(), item.getProbability()))
                .sorted((a, b) -> Double.compare(b.getProbability(), a.getProbability()))
                .collect(Collectors.toList());
    }
}