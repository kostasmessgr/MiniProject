package com.kostisProjects.MiniProject.converters;

import com.kostisProjects.MiniProject.models.Product;
import com.mongodb.DBObject;
import org.json.simple.JSONObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import java.util.List;

public class MongoObjectToProductConverter {

    private Product product;

    public static Product convertFromJSON(JSONObject jsonObject){
    Product result = new Product(jsonObject.get("_id.oid").toString(),jsonObject.get("title").toString(),(int)jsonObject.get("price"),jsonObject.get("brand").toString(),jsonObject.get("description").toString(),jsonObject.get("date").toString(),jsonObject.get("asin").toString());
    return result;
    }


}




//}
