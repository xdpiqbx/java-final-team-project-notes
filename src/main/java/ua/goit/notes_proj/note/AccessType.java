package ua.goit.notes_proj.note;

public enum AccessType {
    PRIVATE("private"), PUBLIC("public");
    private final String type;
    AccessType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
