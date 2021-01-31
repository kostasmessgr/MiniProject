package com.kostisProjects.MiniProject.services;

import com.kostisProjects.MiniProject.converters.MongoObjectToProductConverter;
import com.kostisProjects.MiniProject.models.Product;
import com.kostisProjects.MiniProject.repositories.ProductRepository;
import com.mongodb.DBObject;
import netscape.javascript.JSObject;
import org.apache.commons.lang3.math.NumberUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


@ComponentScan
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    // private static Pageable pageable;

    public void loadJSONs(String filename){
        File f = new File(filename);
        JSONParser parser = new JSONParser();
        String line;
        Product p;
        System.out.println("Load Json");
      try {
          JSONArray array = (JSONArray) parser.parse(new FileReader(f));
          //Scanner sc = new Scanner(f);
          //while(sc.hasNext()){
              //line = sc.nextLine();
              //JSONObject object = (JSONObject) parser.parse(line);
              for(int i=0;i<array.size();i++){
                p = MongoObjectToProductConverter.convertFromJSON((JSONObject) array.get(i));
                System.out.println(i+" "+p.toString());
                productRepository.save(p);
              }
      }catch (FileNotFoundException e){
          e.printStackTrace();
      }catch (ParseException e){
          e.printStackTrace();
      }catch (IOException e){
          e.printStackTrace();
      }
    }


    public Page<Product> listAllProducts(Pageable pageable) {

        Product p;
        /*for(int i=0;i<500000;i++){
            p = new Product("id"+i,"title",34,"brand","this is a product","date","asin");
            System.out.println(i);
            productRepository.save(p);
        }*/

        System.out.println("listAllProducts");
        //List<Product> res = productRepository.findAll();
        Page<Product> results = productRepository.findAll(pageable);
        //System.out.println(res);
        //System.out.println(results);
        //System.out.println(results.getContent()+" "+results.hasContent()+results.getTotalElements());
        //System.out.println(res.toString());
        return results;
    }

    public Page<Product> isContained(String content,Pageable pageable){
        System.out.println("contains");
        Product p= null;
        if(NumberUtils.isCreatable(content)){
            p = new Product(content, content, Integer.parseInt(content), content, content, content, content);
        }else {
            p = new Product();
            p.setId(content);
            p.setAsin(content);
            p.setBrand(content);
            p.setDescription(content);
            p.setDate(content);
            p.setTitle(content);
        }
            //p = new Product(content, content, (Integer)null, content, content, content, content);
            return contains(p,pageable);
    }

    public Page<Product> contains(Product p,Pageable pageable){
        //System.out.println(p.getPrice());
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Product> productExample = Example.of(p,matcher);
        System.out.println(matcher.toString());
        System.out.println(productExample.toString());
        return productRepository.findAll(productExample,pageable);
    }
}