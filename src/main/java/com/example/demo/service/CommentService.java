package com.example.demo.service;


import com.example.demo.model.CommentProduct;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<CommentProduct> create(String username, Long productId, String comment);
    Optional<CommentProduct> update(Long id, String comment);
    Optional<CommentProduct> delete(Long commentId);
    Optional<Optional<CommentProduct>> findById(Long id);
    List<CommentProduct> findAllByProductId(Long id);
}
