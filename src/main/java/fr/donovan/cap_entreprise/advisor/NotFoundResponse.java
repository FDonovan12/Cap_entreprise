package fr.donovan.cap_entreprise.advisor;

import fr.donovan.cap_entreprise.custom_response.ResponseException;
import fr.donovan.cap_entreprise.exception.NotFoundCapEntrepriseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundResponse {

    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) // Modifie le code HTTP de la réponse
    @ExceptionHandler(NotFoundCapEntrepriseException.class)
        // L'exception qui doit être "catch"
    ResponseException notFoundResponseHandler(NotFoundCapEntrepriseException e) {
        return new ResponseException(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                e.getType(),
                e.getField(),
                e.getValue(),
                e.getMessage()
        );
    }

}
