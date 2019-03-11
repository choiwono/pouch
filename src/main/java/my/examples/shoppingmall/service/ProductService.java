package my.examples.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import my.examples.shoppingmall.domain.Order;
import my.examples.shoppingmall.domain.Product;
import my.examples.shoppingmall.dto.ProductItem;
import my.examples.shoppingmall.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> findAllProduct(){
        return productRepository.getFindAll();
    }

    @Transactional(readOnly = true)
    public Product findByIdProduct(Long id){
        return productRepository.findProductById(id);
    }

    @Transactional
    public List<ProductItem> findMyProductList(Map<Long, Integer> cart) {
        List<ProductItem> list = new ArrayList<>();
        for (Long key : cart.keySet()) {
            Product product = productRepository.findProductById(key);
            ProductItem productItem = new ProductItem();
            productItem.setId(product.getId());
            productItem.setAmount(cart.get(key));
            productItem.setPrice(product.getPrice());
            productItem.setName(product.getName());
            productItem.setFiles(product.getFileImages());
            list.add(productItem);
        }
        return list;
    }

    public int findTotalPrice(List<ProductItem> products) {
        int result = 0;
        for(ProductItem pr : products){
            result += (pr.getPrice() * pr.getAmount());
        }
        return result;
    }
}
