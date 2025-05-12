package ch.zhaw.dto;

/**
 * Diese Klasse ist ein Beispiel für eine DTO-Klasse (Data Transfer Object).
 * Sie wird verwendet, um Informationen über das Ergebnis einer Klassifikation zu speichern.
 * In diesem Fall enthält die Klasse den Namen der Klasse und die Wahrscheinlichkeit.
 */
public class ClassificationResult {
    private String className;
    private double probability;

    public ClassificationResult() {
    }

    public ClassificationResult(String className, double probability) {
        this.className = className;
        this.probability = probability;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
}
