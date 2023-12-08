package com.example.exp.handler;


import com.example.exp.AlreadyExistsException;
import com.example.exp.EmailAlreadyExistsException;
import com.example.exp.ItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({
            AlreadyExistsException.class,
            EmailAlreadyExistsException.class,
            ItemNotFoundException.class

    })
    public ResponseEntity<?> handlerExc(RuntimeException runtimeException) {
        log.warn(runtimeException.getMessage());
        return ResponseEntity.badRequest().body(runtimeException.getMessage());
    }


    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> handleNotValidException(RuntimeException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}