package com.kostisProjects.MiniProject.services;

import com.kostisProjects.MiniProject.models.Product;
import com.kostisProjects.MiniProject.repositories.ProductRepository;
import org.springframework.data.domain.*;


public class ProductServiceImpl {

    private ProductRepository productRepository;
    private static Pageable pageable;

    public Page<Product> listAllProducts(Pageable p) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> findByTitle(String title, Pageable pageable){
        Product p = new Product();
        p.setTitle(title);
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.EXACT);
        Example<Product> productExample = Example.of(p,matcher);
        return productRepository.findAll(productExample,pageable);
    }


    public Page<Product> findByBrand(String brand){
        Product p = new Product();
        p.setBrand(brand);
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.EXACT);
        Example<Product> productExample = Example.of(p,matcher);
        return productRepository.findAll(productExample,pageable);
    }

    Page <Product> findByDate(String date){
        Product p = new Product();
        p.setDate(date);
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.EXACT);
        Example<Product> productExample = Example.of(p,matcher);
        return productRepository.findAll(productExample,pageable);
    }

    public Page <Product> findByProductId(String id){
        Product p = new Product();
        p.setId(id);
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.EXACT);
        Example<Product> productExample = Example.of(p,matcher);
        return productRepository.findAll(productExample,pageable);
    }

    public Page<Product> containedInDescription(String content){
        Product p = new Product();
        p.setDescription(content);
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Product> productExample = Example.of(p,matcher);
        return productRepository.findAll(productExample,pageable);
    }

    public Page <Product> containedInTitle(String content){
        Product p = new Product();
        p.setTitle(content);
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Product> productExample = Example.of(p,matcher);
        return productRepository.findAll(productExample,pageable);
    }

    public Page<Product> containedInId(String content){
        Product p = new Product();
        p.setId(content);
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Product> productExample = Example.of(p,matcher);
        return productRepository.findAll(productExample,pageable);
    }

}
