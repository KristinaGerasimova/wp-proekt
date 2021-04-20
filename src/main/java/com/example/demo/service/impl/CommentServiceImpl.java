package com.example.demo.service.impl;

import com.example.demo.model.CommentProduct;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.model.exception.UserNotFoundException;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(UserRepository userRepository, ProductRepository productRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Optional<CommentProduct> create(String username, Long productId, String comment) {
        User user = this.userRepository.findByUsername(username).get();
        Product product = this.productRepository.findById(productId).get();
        CommentProduct commentProduct = new CommentProduct(user, product, comment);
        return Optional.of(this.commentRepository.save(commentProduct));
    }

    @Override
    public Optional<CommentProduct> update(Long id, String comment) {
        CommentProduct commentProduct = this.findById(id).get().orElseThrow(()-> new UserNotFoundException("Comment not found"));
        commentProduct.setComment(comment);
        return Optional.of(this.commentRepository.save(commentProduct));
    }

    @Override
    public Optional<CommentProduct> delete(Long commentId) {
        CommentProduct commentProduct = this.findById(commentId).get().orElseThrow(()-> new UserNotFoundException("Comment not found"));
        this.commentRepository.delete(commentProduct);
        return Optional.of(commentProduct);
    }

    @Override
    public Optional<Optional<CommentProduct>> findById(Long id) {
        return Optional.of(this.commentRepository.findById(id));

    }

    @Override
    public List<CommentProduct> findAllByProductId(Long id) {
        return this.commentRepository.findAllByProduct_Id(id);
    }
}
