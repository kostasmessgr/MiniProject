package com.kostisProjects.MiniProject.controllers;
import com.kostisProjects.MiniProject.models.Product;
import com.kostisProjects.MiniProject.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/products")
public class ProductController {

    private static final int DEFAULT_PAGE_SIZE=20;
    private static final int STARTING_PAGE=0;
    private static final int [] AVAILABLE_PAGE_SIZES = {10,20,50};

    private ProductService productService;
    private Page<Product> page;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<Page> getProductById(@PathVariable("id") String id){
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        try {
                page = productService.findByProductId(id,pageable);
            if(page.hasContent()){
                return new ResponseEntity<>(page,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/{title}")
    public ResponseEntity<Page> getProductByTitle(@PathVariable("title") String title){
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        try {
            page = productService.findByProductId(title,pageable);
            if(page.hasContent()){
                return new ResponseEntity<>(page,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
