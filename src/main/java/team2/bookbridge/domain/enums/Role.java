package team2.bookbridge.domain.enums;

public enum Role {
    DONOR("DONOR"),
    RECIPIENT("RECIPIENT");

    String role;

    Role(String role) {
        this.role = role;
    }

    public String value() {
        return role;
    }
}
