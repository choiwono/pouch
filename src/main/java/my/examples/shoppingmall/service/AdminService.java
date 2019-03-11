package my.examples.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import my.examples.shoppingmall.domain.Product;
import my.examples.shoppingmall.domain.ProductCategory;
import my.examples.shoppingmall.repository.ProductCategoryRepository;
import my.examples.shoppingmall.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @Transactional
    public Product addProduct(Product product,Long categoryId){
        Optional<ProductCategory> optionalProductCategory =
                productCategoryRepository.findById(categoryId);
        product.setProductCategory(optionalProductCategory.get());
        return productRepository.save(product);
    }
}
