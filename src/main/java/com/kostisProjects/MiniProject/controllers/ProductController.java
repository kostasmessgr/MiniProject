package com.kostisProjects.MiniProject.controllers;
import com.kostisProjects.MiniProject.models.Product;
import com.kostisProjects.MiniProject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@ComponentScan
@Controller
@RequestMapping("/products")
public class ProductController {

    private static final int DEFAULT_PAGE_SIZE=20;
    private static final int STARTING_PAGE=0;
    private static final int [] AVAILABLE_PAGE_SIZES = {10,20,50};

    @Autowired
    private ProductService productService;

    private Page<Product> page;

    @GetMapping("/products/{id}")
    public ResponseEntity<Page> getProductById(@PathVariable("id") String id,
                                               @PathVariable("size") int size,@PathVariable int number){
        Pageable pageable = PageRequest.of(number,size);
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
    public ResponseEntity<Page> getProductByTitle(@PathVariable("title") String title,
                                                  @PathVariable("size") int size,@PathVariable int number){
        Pageable pageable = PageRequest.of(number,size);
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

    @GetMapping("/products/{date}")
    public ResponseEntity<Page> getProductByDate(@PathVariable("date") String date,
                                                  @PathVariable("size") int size,@PathVariable int number){
        Pageable pageable = PageRequest.of(number,size);
        try {
            page = productService.findByDate(date,pageable);
            if(page.hasContent()){
                return new ResponseEntity<>(page,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/{brand}")
    public ResponseEntity<Page> getProductByBrand(@PathVariable("brand") String brand,
                                                  @PathVariable("size") int size,@PathVariable int number){
        Pageable pageable = PageRequest.of(number,size);
        try {
            page = productService.findByBrand(brand,pageable);
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
