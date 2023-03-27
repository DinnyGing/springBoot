package my.com.springboot.controllers;

import my.com.springboot.models.Post;
import my.com.springboot.repo.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;


@Controller
public class BlogController {


    private PostService postService;
    @Autowired
    public BlogController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/blog")
    public String blogMain(Model model){
        Iterable<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "blog-main";

    }
    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        model.addAttribute("post", new Post());
        return "blog-add";

    }
    @PostMapping("/blog/add")
    public String create(@RequestParam String title, @RequestParam String full_text, @RequestParam String anons){
        Post post = new Post(title, anons, full_text);
        postService.save(post);

        return "redirect:/blog";
    }
    @GetMapping("/blog/{id}")
    public String show(@PathVariable("id") long id, Model model){
        if(!postService.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = postService.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-details";
    }
    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable("id") long id, Model model){
        if(!postService.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = postService.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-edit";
    }
    @PostMapping("/blog/{id}/edit")
    public String update(@PathVariable("id") long id, @RequestParam String title, @RequestParam String full_text, @RequestParam String anons){
        Post post = postService.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postService.save(post);

        return "redirect:/blog";
    }
    @PostMapping("/blog/{id}/remove")
    public String remove(@PathVariable("id") long id){
        Post post = postService.findById(id).orElseThrow();
        postService.delete(post);

        return "redirect:/blog";
    }

}
