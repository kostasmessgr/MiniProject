package com.kostisProjects.MiniProject.repositories;
import com.kostisProjects.MiniProject.models.Product;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@ComponentScan
@Repository
public interface ProductRepository extends MongoRepository<Product,String> {

}
//TypedExample{probe=Product{id='null', title='Title', price='null', brand='null', category='null', asin='null'}, matcher=TypedExampleMatcher{nullHandler=IGNORE, defaultStringMatcher=CONTAINING, propertySpecifiers=org.springframework.data.domain.ExampleMatcher$PropertySpecifiers@0, ignoredPaths=[], defaultIgnoreCase=true, mode=ANY}}
//TypedExample{probe=Product{id='null', title='Elite', price='null', brand='null', category='null', asin='null'}, matcher=TypedExampleMatcher{nullHandler=IGNORE, defaultStringMatcher=CONTAINING, propertySpecifiers=org.springframework.data.domain.ExampleMatcher$PropertySpecifiers@0, ignoredPaths=[], defaultIgnoreCase=true, mode=ANY}}
//TypedExample{probe=Product{id='null', title='Elite', price='null', brand='null', category='null', asin='null'}, matcher=TypedExampleMatcher{nullHandler=IGNORE, defaultStringMatcher=CONTAINING, propertySpecifiers=org.springframework.data.domain.ExampleMatcher$PropertySpecifiers@0, ignoredPaths=[], defaultIgnoreCase=true, mode=ANY}}