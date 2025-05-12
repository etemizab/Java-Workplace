package ch.zhaw.dto;

// Diese Klasse ist ein Beispiel für eine DTO-Klasse (Data Transfer Object).
// Sie wird verwendet, um Informationen über ein Label zu speichern.
// In diesem Fall enthält die Klasse den Namen der Klasse und eine Beschreibung.
public class LabelInfo {
    private String className;
    private String description;

    public LabelInfo() {
    }

    public LabelInfo(String className, String description) {
        this.className = className;
        this.description = description;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
