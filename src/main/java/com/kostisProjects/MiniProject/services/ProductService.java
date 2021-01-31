package com.kostisProjects.MiniProject.services;

import com.kostisProjects.MiniProject.models.Product;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@ComponentScan
@Service
public interface ProductService {

        Page<Product> listAllProducts(Pageable pageable);
        void loadJSONs(String filename);
        Page<Product> isContained(String content, Pageable pageable);
        Page<Product> contains(Product p,Pageable pageable);
}