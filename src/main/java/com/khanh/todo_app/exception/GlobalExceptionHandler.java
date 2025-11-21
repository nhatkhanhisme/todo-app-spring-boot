package com.khanh.todo_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // 1. Đánh dấu đây là nơi xử lý lỗi cho toàn bộ ứng dụng
public class GlobalExceptionHandler {

    // 2. Hứng lỗi Validation (MethodArgumentNotValidException)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Trả về mã lỗi 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // 3. Lặp qua từng lỗi và lấy ra tin nhắn (message) bạn đã viết trong Model
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField(); // Ví dụ: "title"
            String errorMessage = error.getDefaultMessage();    // Ví dụ: "Tiêu đề không được để trống"
            errors.put(fieldName, errorMessage);
        });

        return errors; // Trả về JSON map đẹp đẽ
    }
}
