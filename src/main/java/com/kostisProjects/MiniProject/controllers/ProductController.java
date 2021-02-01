package com.kostisProjects.MiniProject.controllers;
import com.kostisProjects.MiniProject.models.Product;
import com.kostisProjects.MiniProject.services.ProductService;
import com.kostisProjects.MiniProject.services.ProductServiceImpl;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

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


    @GetMapping("/allProducts1")
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
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/allProducts")
    @ResponseBody
    public ResponseEntity<Page> getProducts(@RequestBody JSONObject request){
        String req = request.toJSONString();
        System.out.println(req);
        Integer pageNumber =(int)request.get("page");
        Integer pageSize = (int)request.get("per");
        JSONParser parser = new JSONParser();
        try{
        JSONObject obj  = (JSONObject) parser.parse(String.valueOf(request));
        JSONArray filteredarray = (JSONArray) obj.get("filtered");
        JSONArray sortedarray = (JSONArray) obj.get("sorted");
        List<Object> values =  new ProductServiceImpl().getVals(filteredarray,sortedarray,pageSize,pageNumber);

            String sortedKey = values.get(0)==null? null:values.get(0).toString();
            String filterKey = values.get(1)==null? null:values.get(1).toString();
            String filterValue = values.get(2)==null? null:values.get(2).toString();
            Pageable pageable = values.get(3)==null? null:(Pageable) values.get(3);

        //System.out.println(pageable.toString()+" key: "+filterKey+" value: "+filterValue);
            if(filterKey==null && sortedKey==null ) {
                pageable = PageRequest.of(pageNumber,pageSize);
                Page<Product> res = productService.listAllProducts(pageable);
                return new ResponseEntity<>(res,HttpStatus.OK);
            }else if(sortedKey == null){
                pageable = PageRequest.of(pageNumber,pageSize);
                //Page<Product> res = productService.listAllProducts(pageable);
                //return new ResponseEntity<>(res,HttpStatus.OK);
            }else if(filterKey == null){
                pageable = PageRequest.of(0,pageSize);
                Page<Product> res = productService.listAllProducts(pageable);
                return new ResponseEntity<>(res,HttpStatus.OK);
            }


            page = productService.isContained(filterKey,filterValue,pageable);
            if(page.hasContent()){
                System.out.println("Has content");
                return new ResponseEntity<>(page,HttpStatus.OK);
            }else{
                System.out.println("Not found");
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}