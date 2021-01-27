package com.kostisProjects.MiniProject.repositories;

import com.kostisProjects.MiniProject.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Product,Long> {

}
