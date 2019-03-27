package my.examples.pouch.repository.Custom;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import my.examples.pouch.domain.Category;
import my.examples.pouch.domain.QCategory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoryRepositoryImpl extends QuerydslRepositorySupport implements CategoryRepositoryCustom {


    public CategoryRepositoryImpl() {
        super(Category.class);
    }

    @Override
    public List<Category> searchCategory(String searchStr) {

        QCategory qCategory = QCategory.category;
        JPQLQuery<Category> jpqlQuery = from(qCategory);
        jpqlQuery.where(qCategory.categoryName.contains(searchStr));
        return jpqlQuery.fetch();
    }


    @Override
    public long getProductsCount(String searchStr) {
        return 0;
    }
}
