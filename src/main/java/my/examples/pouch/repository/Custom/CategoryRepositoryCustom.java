package my.examples.pouch.repository.Custom;

import my.examples.pouch.domain.Category;
import my.examples.pouch.domain.Link;

import java.util.List;

public interface CategoryRepositoryCustom {
    public List<Category> searchCategory(String searchStr);

    long countSearchCategory(String searchStr);
}
