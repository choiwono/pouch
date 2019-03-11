package my.examples.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import my.examples.shoppingmall.domain.ProductCategory;
import my.examples.shoppingmall.repository.ProductCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    @Transactional(readOnly=true)
    public List<ProductCategory> getFindAll() {
        return productCategoryRepository.getFindAll();
    }
}
