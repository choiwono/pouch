package my.examples.pouch.repository.Custom;

import my.examples.pouch.domain.Category;

import java.util.List;

public interface CategoryRepositoryCustom {
    public List<Category> searchCategory(String searchStr);
    public long getProductsCount(String searchStr);
}
