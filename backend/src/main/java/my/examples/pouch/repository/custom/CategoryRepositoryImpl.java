package my.examples.pouch.repository.custom;

import com.querydsl.jpa.JPQLQuery;
import my.examples.pouch.domain.*;
import my.examples.pouch.dto.CustomCategory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CategoryRepositoryImpl extends QuerydslRepositorySupport implements CategoryRepositoryCustom {

    public CategoryRepositoryImpl() {
        super(Category.class);
    }

//    @Override
//    public List<CustomCategory> searchCategory(String searchStr) {
//
//        QCategory qCategory = QCategory.category;
//        JPQLQuery<CustomCategory> jpqlQuery = from(qCategory);
//        jpqlQuery.where(qCategory.categoryName.contains(searchStr));
//        return jpqlQuery.fetch();
//    }

    @Override
    public long countSearchCategory(String searchStr) {
        QCategory qCategory = QCategory.category;
        JPQLQuery<Category> jpqlQuery = from(qCategory);
        jpqlQuery.where(qCategory.categoryName.contains(searchStr));
        return jpqlQuery.fetchCount();
    }
}
