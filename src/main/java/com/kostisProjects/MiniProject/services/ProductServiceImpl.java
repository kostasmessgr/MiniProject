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
import java.util.ArrayList;
import java.util.List;


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

    public Page<Product> isContained(String key,String value,Pageable pageable){
        //System.out.println("contains"+key+" "+value);
        //System.out.println(key.equals("title"));
        Product p= new Product();
        //System.out.println(key);
        try {
            switch (key) {
                case"id":
                    p.setId(value);
                    break;
                case"main_cat":
                    p.setCategory(value);
                    break;
                case"title":
                    p.setTitle(value);
                    break;
                    //System.out.println(p.toString());
                case"price":
                    if(NumberUtils.isCreatable(value)) p.setPrice(value);
                    break;
                case"asin":
                    p.setAsin(value);
                    break;
                case"brand":
                    System.out.println("Date"+ key.equals("date"));
                    p.setBrand(value);
                    break;
                case"description":
                    System.out.println("Description"+ key.equals("date"));
                    //p.setDescription(value);
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(p.toString());
        return contains(p,pageable);
    }

    public Page<Product> contains(Product p,Pageable pageable){
        System.out.println(2);
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Product> productExample = Example.of(p,matcher);
        System.out.println(matcher.toString());
        System.out.println(productExample.toString());
        Page<Product> result =  productRepository.findAll(productExample,pageable);
        System.out.println(result.getContent()+" "+result.getTotalElements());
        return result;
    }

    public List<Object> getVals(JSONArray filteredarray, JSONArray sortedarray, int pageSize, int pageNumber){
      List<Object> result = new ArrayList<>();
        String filterKey=null;
        String filterValue=null;
        String sortedKey=null;
        Boolean descValue=null;

        if(filteredarray.size()>0) {
            JSONObject filtered = (JSONObject) filteredarray.get(0);
            filterKey= filtered.get("id").toString();
            filterValue= filtered.get("value").toString();
        }
        System.out.println(sortedarray.size());
        if(sortedarray.size()>0) {
            JSONObject sorted = (JSONObject) sortedarray.get(0);
            sortedKey = sorted.get("id").toString();
            descValue = (Boolean) sorted.get("desc");
        }
        System.out.println("Sorted "+sortedKey+" "+descValue);
        System.out.println("Filtered "+filterKey+" "+filterValue);

        Pageable pageable = null;
        //switch(order){
        if(descValue!=null) {
            if (descValue) {
                pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC,"id",sortedKey));
            } else {
                pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC,"id",sortedKey));
            }
        }else{
            pageable = PageRequest.of(pageNumber,pageSize);
        }
        result.add(sortedKey);
        result.add(filterKey);
        result.add(filterValue);
        result.add(pageable);
      return result;
    }
}