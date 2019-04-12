package my.examples.pouch.repository.custom;

import my.examples.pouch.domain.Category;

import java.util.List;

public interface CategoryRepositoryCustom {
    public List<Category> searchCategory(String searchStr);
    long countSearchCategory(String searchStr);
}
