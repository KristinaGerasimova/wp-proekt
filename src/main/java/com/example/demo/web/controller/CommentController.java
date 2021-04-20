package com.example.demo.web.controller;

import com.example.demo.model.CommentProduct;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import com.example.demo.service.CommentService;
import com.example.demo.service.ProductService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CommentController {

    public final CommentService commentService;
    public final ProductService productService;
    public final AuthService authService;

    public CommentController(CommentService commentService, ProductService productService, AuthService authService) {
        this.commentService = commentService;
        this.productService = productService;
        this.authService = authService;
    }

    @GetMapping("/comments/{id}/create")
    public String createCommentPage(@PathVariable Long id, Model model)
    {
        Product product = this.productService.findById(id).get();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = (User) this.authService.loadUserByUsername(username);

        List<CommentProduct> commentList = this.commentService.findAllByProductId(id);

        model.addAttribute("product", product);
        model.addAttribute("user", user);
        model.addAttribute("comments", commentList);
        model.addAttribute("bodyContent", "comment");
        return "master-template";
    }

    @PostMapping("/comments/{id}/add")
    public String addComment(@PathVariable Long id,
                             @RequestParam String username,
                             @RequestParam String comment)
    {
        this.commentService.create(username,id,comment);
        return "redirect:/comments/{id}/create";
    }
}
