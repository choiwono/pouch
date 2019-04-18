package my.examples.pouch.repository.custom;

import my.examples.pouch.dto.CustomCategory;

import java.util.List;

public interface CategoryRepositoryCustom {
//    public List<CustomCategory> searchCategory(String searchStr);
    long countSearchCategory(String searchStr);
}
