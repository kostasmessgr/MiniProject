package com.kostisProjects.MiniProject.controllers;
import com.kostisProjects.MiniProject.models.Product;
import com.kostisProjects.MiniProject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


@RestController
public class ProductController {

    private static final int DEFAULT_PAGE_SIZE=20;
    private static final int STARTING_PAGE=0;
    private static final int [] AVAILABLE_PAGE_SIZES = {10,20,50};

    @Autowired
    private ProductService productService;

    private Page<Product> page;

    @GetMapping("/loadProducts")
    public ResponseEntity<Page> loadallProducts(@PathVariable(value = "size",required = false) Integer size,@PathVariable(value ="number",required = false) Integer number){
        System.out.println("hi");
        if(size==null) size=DEFAULT_PAGE_SIZE;
        if(number==null) number=STARTING_PAGE;
        System.out.println("Inside allProducts"+size+" "+number);
        Pageable pageable = PageRequest.of(number,size);
        try {
            productService.loadJSONs("C:\\Users\\kosta\\OneDrive\\Υπολογιστής\\Dataset\\Products.json");
            if(page.hasContent()){
                System.out.println("Has content");
                return new ResponseEntity<>(page,HttpStatus.OK);
            }else{
                System.out.println("Not found");
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/allProducts")
    @ResponseBody
    public ResponseEntity<Page> getallProducts(@RequestParam(defaultValue = "20",required = false) Integer pageSize,@RequestParam(defaultValue = "0",required = false) Integer pageNumber){
        System.out.println("Inside allProducts"+pageSize+" "+pageNumber);
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        try {
            page = productService.listAllProducts(pageable);
            if(page.hasContent()){
                System.out.println("Has content");
                return new ResponseEntity<>(page,HttpStatus.OK);
            }else{
                System.out.println("Not found");
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/contains")
    @ResponseBody
    public ResponseEntity<Page> getProductById(@RequestParam(required = true) String context,
                                               @RequestParam(defaultValue = "20",required = false) Integer pageSize,@RequestParam(defaultValue = "0",required = false) Integer pageNumber){
        String contex = "a";
        System.out.println(context);
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        try {
            page = productService.isContained(context,pageable);
            if(page.hasContent()){
                System.out.println("Has content");
                return new ResponseEntity<>(page,HttpStatus.OK);
            }else{
                System.out.println("Not found");
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}