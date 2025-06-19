package com.erbalkan.blog_project.core.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
// HttpMessageNotReadableException , JSON hatalarını yakalamak için kullanılır.
// Kullanıcı POST isteğinde JSON’ı eksik veya hatalı gönderirse, bu hata fırlatılır.
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.erbalkan.blog_project.core.result.error.ErrorDataResult;

@RestControllerAdvice
// Tüm Controller’lar için hata yönetimi yapacak özel bir sınıf.
// Bu sınıf, doğrulama hatalarını, JSON okuma hatalarını ve genel hataları yakalayacak.
// @RestControllerAdvice nedir?
// Bu sınıf artık global bir hata yöneticisi oldu.
// Tüm Controller'lar içinde oluşan hataları bu sınıf merkezi olarak yakalayacak.
public class GlobalExceptionHandler {

    @ExceptionHandler 
    @ResponseStatus(HttpStatus.BAD_REQUEST) 
    public ErrorDataResult<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        // Bu metot, @Valid anotasyonuyla gelen ve hatalı olan alanları yakalar.
        // @ExceptionHandler: Bu metot bir exception (hata) yakalayacak.
        // MethodArgumentNotValidException: Alanlara (field) özel validasyon hatalarını temsil eder.
        // @ResponseStatus(HttpStatus.BAD_REQUEST): 400 hatası dönecek (kullanıcı hatası).
        Map<String, String> validationErrors = new HashMap<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
            // Tüm hatalı alanları tek tek geziyoruz:
            // getField(): Hatalı olan alanın adı (örneğin: "title")
            // getDefaultMessage(): Hata mesajı (örneğin: "Başlık boş olamaz")
            // Tüm bu hataları bir Map yapısına koyuyoruz.
            // Yani: {"title": "Başlık boş olamaz", "content": "İçerik zorunlu"} gibi.
        }

        return new ErrorDataResult<>(validationErrors, "Doğrulama hataları oluştu.");

        // Son olarak kullanıcıya düzgün bir cevap dönüyoruz.
    
}




    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        /*
        Bu metot, kullanıcı JSON’ı bozuk gönderirse çalışır.
        Örneğin:
        {
            "title": "Merhaba"
            // content yok, virgül eksik!
        }

        Bu durumda Spring bu hatayı fırlatır ve biz de yukarıdaki metotla bunu yakalarız.
        */
        
        
        return new ErrorDataResult<>("JSON formatı hatalı veya eksik alan var.");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDataResult<String> handleGenericException(Exception ex) {
        /*
        Bu metot, hiçbir özel tipe uymayan genel hataları yakalar. (Genel Hatalar (Sunucu çökerse vs.))
         */
        return new ErrorDataResult<>("Sunucu hatası: " + ex.getMessage());
    }
}

