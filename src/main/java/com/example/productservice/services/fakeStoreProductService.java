package com.example.productservice.services;

import com.example.productservice.DTO.FakeStoreProductDto;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class fakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    fakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    private Product convertfakestoredtoIntoproduct(FakeStoreProductDto fakeStoreProductDto){
        Product product= new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());
        Category category= new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
    @Override
    public Product getProductById(Long id) {
      FakeStoreProductDto fakeStoreProductDtoResponseEntity= restTemplate.getForObject("https://fakestoreapi.com/products/1" + id, FakeStoreProductDto.class);
        return convertfakestoredtoIntoproduct(fakeStoreProductDtoResponseEntity);
    }

    @Override
    public List<Product> getAllProducts() {
       List<Product>  allProducts = new ArrayList<>();

       FakeStoreProductDto[] fakeStoreProductDtos= restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
         for(FakeStoreProductDto fake:fakeStoreProductDtos){
             Product pro= new Product();
             pro=convertfakestoredtoIntoproduct(fake);
             allProducts.add(pro);
         }
         return allProducts;
    }

    @Override
    public Product createProduct() {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product,FakeStoreProductDto.class );
        HttpMessageConverterExtractor<FakeStoreProductDto  > responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class,
                restTemplate.getMessageConverters() );
        FakeStoreProductDto fakeStoreProductDto= restTemplate.execute("https://fakestoreapi.com/products/" +id, HttpMethod.PUT, requestCallback, responseExtractor);
         return convertfakestoredtoIntoproduct(fakeStoreProductDto);
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
