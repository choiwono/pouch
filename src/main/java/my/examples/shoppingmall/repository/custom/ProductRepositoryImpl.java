package my.examples.shoppingmall.repository.custom;

import com.querydsl.jpa.JPQLQuery;
import my.examples.shoppingmall.domain.Product;
import my.examples.shoppingmall.domain.QProduct;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProductRepositoryImpl extends QuerydslRepositorySupport implements  ProductRepositoryCustom {

    public ProductRepositoryImpl(){
        super(Product.class);
    }

    @Override
    public List<Product> getProducts(int start, int limit, String searchStr) {
        QProduct qProduct = QProduct.product;
        JPQLQuery<Product> jpqlQuery = from(qProduct).innerJoin(qProduct.productCategory).fetchJoin()
                                       .innerJoin(qProduct.fileImages).fetchJoin()
                                       .distinct();

        jpqlQuery.where(qProduct.name.like("%"+searchStr+"%"));
        jpqlQuery.orderBy(qProduct.id.desc());
        jpqlQuery.offset(start).limit(limit);
        return jpqlQuery.fetch();
    }

    @Override
    public long getProductsCount(String searchStr) {
        QProduct qProduct = QProduct.product;
        JPQLQuery<Product> jpqlQuery = from(qProduct).innerJoin(qProduct.productCategory).fetchJoin()
                .innerJoin(qProduct.fileImages).fetchJoin()
                .distinct();
        jpqlQuery.where(qProduct.name.like("%"+searchStr+"%"));
        return jpqlQuery.fetchCount();
    }
}
