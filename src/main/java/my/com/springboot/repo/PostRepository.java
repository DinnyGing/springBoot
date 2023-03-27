package my.com.springboot.repo;

import my.com.springboot.models.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {
    List<Post>  posts = new ArrayList<>();

    public PostRepository() {
        Post post1 = new Post("First", "Beginning", "This is new post");
        Post post2 = new Post("What is next?", "Something  interesting", "This is second post");
        Post post3 = new Post("What is next? Part 2", "Continue preview", "This is not last post");
        posts.addAll(List.of(new Post[]{post1, post2, post3}));
    }
    public Iterable<Post> findAll(){
        return posts;
    }
    public void save(Post post){
        posts.add(post);
    }
    public boolean existsById(long id){
        return !findById(id).isEmpty();
    }
    public Optional<Post> findById(long id){
        return posts.stream().filter(i -> i.getId() == id).findAny();
    }
    public void delete(Post post){
        posts.remove(post);
    }
}
