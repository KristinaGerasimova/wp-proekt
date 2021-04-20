package com.example.demo.service.impl;

import com.example.demo.model.Dto.ProductDto;
import com.example.demo.model.Product;
import com.example.demo.model.ShoppingCart;
import com.example.demo.model.exception.ProductNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ShoppingCartRepository;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ShoppingCartRepository shoppingCartRepository) {
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }


    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, Double price, Integer quantity, String img) {
        this.productRepository.deleteByName(name);
        return Optional.of(this.productRepository.save(new Product(name, price, quantity, img)));
    }

    @Override
    public Optional<Product> save(ProductDto productDto) {
        this.productRepository.deleteByName(productDto.getName());
        return Optional.of(this.productRepository.save(new Product(productDto.getName(), productDto.getPrice(), productDto.getQuantity(), productDto.getImg())));
    }

    @Override
    @Transactional
    public Optional<Product> edit(Long id, String name, Double price, Integer quantity, String img) {
        Product product = this.productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException(id));

        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setImg(img);

        return Optional.of(this.productRepository.save(product));
    }

    @Override
    public Optional<Product> edit(Long id, ProductDto productDto) {
        Product product = this.productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException(id));

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setImg(productDto.getImg());

        return Optional.of(this.productRepository.save(product));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        List<ShoppingCart> carts = this.shoppingCartRepository.findAll();

        for(ShoppingCart shoppingCart : carts)
        {
            shoppingCart.getProducts().removeIf(product -> product.getId().equals(id));
        }
        this.productRepository.deleteById(id);
    }
}
