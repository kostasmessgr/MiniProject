package com.kostisProjects.MiniProject.services;

import com.kostisProjects.MiniProject.models.Product;
import com.kostisProjects.MiniProject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@ComponentScan
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

   // private static Pageable pageable;

    public Page<Product> listAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> findByTitle(String title, Pageable pageable){
        Product p = new Product();
        p.setTitle(title);
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.EXACT);
        Example<Product> productExample = Example.of(p,matcher);
        return productRepository.findAll(productExample,pageable);
    }


    public Page<Product> findByBrand(String brand,Pageable pageable){
        Product p = new Product();
        p.setBrand(brand);
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.EXACT);
        Example<Product> productExample = Example.of(p,matcher);
        return productRepository.findAll(productExample,pageable);
    }

    public Page <Product> findByDate(String date,Pageable pageable){
        Product p = new Product();
        p.setDate(date);
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.EXACT);
        Example<Product> productExample = Example.of(p,matcher);
        return productRepository.findAll(productExample,pageable);
    }

    public Page <Product> findByProductId(String id,Pageable pageable){
        Product p = new Product();
        p.setId(id);
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.EXACT);
        Example<Product> productExample = Example.of(p,matcher);
        return productRepository.findAll(productExample,pageable);
    }

    public Page<Product> containedInDescription(String content,Pageable pageable){
        Product p = new Product();
        p.setDescription(content);
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Product> productExample = Example.of(p,matcher);
        return productRepository.findAll(productExample,pageable);
    }

    public Page <Product> containedInTitle(String content,Pageable pageable){
        Product p = new Product();
        p.setTitle(content);
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Product> productExample = Example.of(p,matcher);
        return productRepository.findAll(productExample,pageable);
    }

    public Page<Product> containedInId(String content,Pageable pageable){
        Product p = new Product();
        p.setId(content);
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Product> productExample = Example.of(p,matcher);
        return productRepository.findAll(productExample,pageable);
    }

}
