package fr.donovan.cap_entreprise.exception;

import lombok.Getter;

@Getter
public class NotFoundCapEntrepriseException extends RuntimeException {

    private final String type;

    private final String field;

    private final Object value;

    public NotFoundCapEntrepriseException(String type, String field, Object value) {
        super("Entity not found");
        this.type = type;
        this.field = field;
        this.value = value;
    }
}
