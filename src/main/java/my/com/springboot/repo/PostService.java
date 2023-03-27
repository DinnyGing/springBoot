package my.com.springboot.repo;

import my.com.springboot.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;
    @Autowired

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    public Iterable<Post> findAll(){
        return postRepository.findAll();
    }
    public void save(Post post){
        postRepository.save(post);
    }
    public boolean existsById(long id){
        return postRepository.existsById(id);
    }
    public Optional<Post> findById(long id){
        return postRepository.findById(id);
    }
    public void delete(Post post){
        postRepository.delete(post);
    }
}
