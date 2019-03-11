package my.examples.shoppingmall.repository.custom;

import my.examples.shoppingmall.domain.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    public List<Product> getProducts(int start, int limit, String searchStr);
    public long getProductsCount(String searchStr);
}
