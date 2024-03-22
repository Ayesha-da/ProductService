package com.example.productservice.services;

import com.example.productservice.DTO.fakeStoreProductDto;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class fakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    fakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    private Product convertfakestoredtoIntoproduct(fakeStoreProductDto fakeStoreProductDto){
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
      fakeStoreProductDto fakeStoreProductDtoResponseEntity= restTemplate.getForObject("https://fakestoreapi.com/products/1" + id, fakeStoreProductDto.class);
        return convertfakestoredtoIntoproduct(fakeStoreProductDtoResponseEntity);
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product createProduct() {
        return null;
    }

    @Override
    public Product updateProduct(Long id) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
