package com.erbalkan.blog_project.service.concretes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.erbalkan.blog_project.core.result.DataResult;
import com.erbalkan.blog_project.core.result.Result;
import com.erbalkan.blog_project.core.result.error.ErrorDataResult;
import com.erbalkan.blog_project.core.result.success.SuccessDataResult;
import com.erbalkan.blog_project.core.result.success.SuccessResult;
import com.erbalkan.blog_project.dto.post.requests.PostCreateRequest;
import com.erbalkan.blog_project.dto.post.requests.UpdatePostRequest;
import com.erbalkan.blog_project.dto.post.responses.PostResponse;
import com.erbalkan.blog_project.model.Post;
import com.erbalkan.blog_project.repository.PostRepository;
import com.erbalkan.blog_project.service.abstracts.PostService;

@Service // Spring'in bu sınıfı bir servis olarak tanımlamasını sağlar.
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Result createPost(PostCreateRequest request) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setAuthor(request.getAuthor());
        postRepository.save(post);
        return new SuccessResult("Post başarıyla eklendi.");
    }

    @Override
    public DataResult<List<PostResponse>> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostResponse> responseList = posts.stream()
                .map(p -> new PostResponse(p.getId(), p.getTitle(), p.getContent(), p.getAuthor()))
                .collect(Collectors.toList());
                // Bu, Post nesnelerini PostResponse nesnelerine dönüştürür.
                // Stream API kullanarak, her Post nesnesi için yeni bir 
                // PostResponse nesnesi oluşturur ve bunları bir listeye toplar.
                // PostResponse, Post nesnesinin DTO'sudur ve istemciye gönderilecek veri yapısını temsil eder.
                // Bu dönüşüm, genellikle veri güvenliği ve performans açısından önemlidir.
        return new SuccessDataResult<>(responseList, "Postlar listelendi.");
        // Başarılı bir şekilde PostResponse listesini döner.
    }

    @Override
    public DataResult<String> updatePost(Long postId, UpdatePostRequest request) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        // optionalPost, postId ile eşleşen bir Post nesnesi arar.
        // Eğer böyle bir Post yoksa, optionalPost boş olur.

        if (optionalPost.isEmpty()) {
            // Eğer optionalPost boş ise, yani güncellenecek post bulunamazsa
            // bu durumda hata mesajı ile birlikte bir ErrorDataResult döner.
            return new ErrorDataResult<>("Güncellenecek post bulunamadı.");
        }

        Post postToUpdate = optionalPost.get();
        // Eğer post bulunursa, optionalPost'tan Post nesnesini alır.
        // get() metodu, Optional içindeki değeri alır. Eğer değer yoksa
        // NoSuchElementException fırlatır, ancak burada zaten boş olup olmadığını kontrol ettik.
        postToUpdate.setTitle(request.getTitle());
        // Post nesnesinin başlığını, UpdatePostRequest'ten gelen başlık ile günceller.
        postToUpdate.setContent(request.getContent());
        // Post nesnesinin içeriğini, UpdatePostRequest'ten gelen içerik ile günceller.

        postRepository.save(postToUpdate);
        // Güncellenmiş Post nesnesini veritabanına kaydeder.
        return new SuccessDataResult<>("Post başarıyla güncellendi.");
        // Başarılı bir şekilde güncelleme mesajı ile birlikte SuccessDataResult döner.
    }

    @Override
    public DataResult<String> deletePost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        // optionalPost, id ile eşleşen bir Post nesnesi arar.

        if (optionalPost.isEmpty()) {
            // Eğer optionalPost boş ise, yani silinecek post bulunamazsa
            // bu durumda hata mesajı ile birlikte bir ErrorDataResult döner.
            return new ErrorDataResult<>("Silinecek post bulunamadı.");
        }
        postRepository.deleteById(id);
        // Eğer post bulunursa, postRepository.deleteById(id) ile postu siler.
        // deleteById metodu, verilen id'ye sahip Post nesnesini veritabanından siler.
        return new SuccessDataResult<>("Post başarıyla silindi.");
        // Başarılı bir şekilde silme mesajı ile birlikte SuccessDataResult döner.
    }
}
