package com.kostisProjects.MiniProject.services;

import com.kostisProjects.MiniProject.models.Product;
import com.kostisProjects.MiniProject.repositories.ProductRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@ComponentScan
@Service
public interface ProductService {

        public Page<Product> listAllProducts(Pageable pageable);
        public Page<Product> findByTitle(String title, Pageable pageable);
        public Page<Product> findByBrand(String brand,Pageable pageable);
        public Page<Product> findByDate(String date,Pageable pageable);
        public Page<Product> findByProductId(String id,Pageable pageable);
        public Page<Product> containedInDescription(String content,Pageable pageable);
        public Page<Product> containedInTitle(String content,Pageable pageable);
        public Page<Product> containedInId(String content,Pageable pageable);
}
