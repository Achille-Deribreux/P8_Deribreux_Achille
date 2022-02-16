package tourGuide.user.Exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tourGuide.user.Entity.CustomErrorResponse;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionsHandler {

    private Logger logger = LoggerFactory.getLogger(ExceptionsHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleBidListNotFoundException(UserNotFoundException e){
        logger.error(e.getMessage());
        CustomErrorResponse res = new CustomErrorResponse(e.getMessage(),e, HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(res, HttpStatus.CONFLICT);
    }
}
