package com.example.demo.web.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model ){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Product> productList = this.productService.findAll();
        model.addAttribute("products", productList);
        model.addAttribute("bodyContent", "products");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public String deleteProduct(@PathVariable Long id){
        this.productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model){
        if(this.productService.findById(id).isPresent()){
            Product product = this.productService.findById(id).get();
            model.addAttribute("product", product);
            model.addAttribute("bodyContent", "add-product");
            return "master-template";
        }
        return "redirect:/products?error=ProductNotFound";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')") //role_user ke ti bide kaj grade
    public String addProductPage(Model model){
        model.addAttribute("bodyContent", "add-product");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveProduct(@RequestParam(required = false)Long id,
                              @RequestParam String name,
                              @RequestParam Double price,
                              @RequestParam Integer quantity,
                              @RequestParam String img ){
        if(id!=null){
            this.productService.edit(id, name, price, quantity, img);
        }
        else{
            this.productService.save(name,price,quantity,img);
        }
        return "redirect:/products";
    }

}
