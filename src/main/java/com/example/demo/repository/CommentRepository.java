package com.example.demo.repository;

import com.example.demo.model.CommentProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentProduct, Long> {
    List<CommentProduct> findAllByProduct_Id(Long id);
}
