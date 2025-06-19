package com.erbalkan.blog_project.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erbalkan.blog_project.core.result.DataResult;
import com.erbalkan.blog_project.core.result.Result;
import com.erbalkan.blog_project.dto.post.requests.PostCreateRequest;
import com.erbalkan.blog_project.dto.post.requests.UpdatePostRequest;
import com.erbalkan.blog_project.dto.post.responses.PostResponse;
import com.erbalkan.blog_project.service.abstracts.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController // @RestController, @Controller ve @ResponseBody anotasyonlarının birleşimidir.
// Bu, sınıfın RESTful web servisleri için bir denetleyici olduğunu belirtir.
@RequestMapping("/api/posts") // Bu, tüm isteklerin "/api/posts" yoluna yönlendirileceğini belirtir.
@Tag(name = "Post API", description = "Blog post CRUD işlemleri")
// @Tag, Swagger/OpenAPI belgelerinde bu denetleyicinin açıklamasını sağlar.
// Bu, API belgelerinde bu denetleyicinin ne amaçla kullanıldığını açıklar.
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping // @PostMapping, HTTP POST isteklerini işlemek için kullanılır.
    // Bu, "/api/posts" yoluna gelen POST isteklerini işlemek için kullanılır.
    public Result createPost(@Valid @RequestBody PostCreateRequest request) {
        return postService.createPost(request);
        // @Valid, gelen isteğin doğrulanmasını sağlar. DTO sınıfındaki @NotBlank gibi doğrulama anotasyonlarının çalışmasını sağlar.
        // @RequestBody, isteğin gövdesindeki JSON verisini PostCreateRequest nesnesine dönüştürür.
        // PostCreateRequest, yeni bir post oluşturmak için gerekli verileri içerir.
        // postService.createPost(request) çağrısı, PostService arayüzündeki createPost metodunu çağırır.
        // Bu metod, yeni bir post oluşturur ve sonucu döner.
    }

    @GetMapping // @GetMapping, HTTP GET isteklerini işlemek için kullanılır.
    // Bu, "/api/posts" yoluna gelen GET isteklerini işlemek için kullanılır.
    // Bu metod, tüm postları listelemek için kullanılır.
    @Operation(summary = "Tüm post'ları getir", description = "Veritabanındaki tüm blog postlarını döner")
    // @Operation, Swagger/OpenAPI belgelerinde bu metodun açıklamasını sağlar.
    // summary, metodun kısa bir özetini, description ise daha ayrıntılı bir açıklamasını sağlar.
    public DataResult<List<PostResponse>> getAllPosts() {
        return postService.getAllPosts();
        // postService.getAllPosts() çağrısı, PostService arayüzündeki getAllPosts metodunu çağırır.
        // Bu metod, veritabanındaki tüm postları alır ve PostResponse nesnelerine dönüştürür.
        // Sonuç olarak, PostResponse nesnelerinin listesini ve bir mesajı içeren bir DataResult döner.
        // DataResult, başarılı bir sonuç ve veriyi içeren bir kapsayıcıdır.
        // PostResponse, istemciye gönderilecek post verilerini temsil eder.
        // Bu, genellikle veri güvenliği ve performans açısından önemlidir.
    }

    // Diğer metodlar (updatePost, deletePost vb.) burada tanımlanabilir.

    @PutMapping("/{id}")
    // @PutMapping, HTTP PUT isteklerini işlemek için kullanılır.
    // Bu, "/api/posts/{id}" yoluna gelen PUT isteklerini işlemek için kullanılır.
    public ResponseEntity<?> updatePost(
        @PathVariable Long id, 
        // @PathVariable, URL yolundaki değişkeni alır.
        @RequestBody @Valid UpdatePostRequest updatePostRequest) 
        // @RequestBody, isteğin gövdesindeki JSON verisini UpdatePostRequest nesnesine dönüştürür.
        { 
        DataResult<String> result = postService.updatePost(id, updatePostRequest);
        if (!result.isSuccess()) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    // @DeleteMapping, HTTP DELETE isteklerini işlemek için kullanılır.
    // Bu, "/api/posts/{id}" yoluna gelen DELETE isteklerini işlemek için kullanılır.
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        // @PathVariable, URL yolundaki id değişkenini alır.
        // Bu, silinecek postun ID'sini temsil eder.
        DataResult<String> result = postService.deletePost(id);
        // postService.deletePost(id) çağrısı, PostService arayüzündeki deletePost metodunu çağırır.
        // Bu metod, belirtilen ID'ye sahip postu siler ve sonucu döner. 
        if (!result.isSuccess()) {
            // Eğer silme işlemi başarısız olursa, hata mesajı ile birlikte bir ResponseEntity döner.
            // ResponseEntity, HTTP yanıtını temsil eder.
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result); 
        // ResponseEntity.ok(result) ile başarılı bir yanıt oluşturulur.
    }

}
