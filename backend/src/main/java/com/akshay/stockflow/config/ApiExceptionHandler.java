package com.akshay.stockflow.config;
import org.springframework.dao.DataIntegrityViolationException; import org.springframework.orm.ObjectOptimisticLockingFailureException; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.time.Instant; import java.util.Map;
@RestControllerAdvice public class ApiExceptionHandler {
@ExceptionHandler(ObjectOptimisticLockingFailureException.class) public ResponseEntity<Map<String,Object>> concurrentUpdate(ObjectOptimisticLockingFailureException ex){return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("timestamp",Instant.now().toString(),"status",409,"error","Concurrent update","message","This record changed while you were working. Refresh and retry."));}
@ExceptionHandler(DataIntegrityViolationException.class) public ResponseEntity<Map<String,Object>> constraintViolation(DataIntegrityViolationException ex){return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("timestamp",Instant.now().toString(),"status",409,"error","Data conflict","message","A record with the same unique identifier already exists or a related record prevents this change."));}
}
