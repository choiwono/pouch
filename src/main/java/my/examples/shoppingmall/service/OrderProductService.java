package my.examples.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import my.examples.shoppingmall.domain.Order;
import my.examples.shoppingmall.domain.OrderProduct;
import my.examples.shoppingmall.domain.Product;
import my.examples.shoppingmall.dto.ProductItem;
import my.examples.shoppingmall.repository.OrderProductRepository;
import my.examples.shoppingmall.repository.OrderRepository;
import my.examples.shoppingmall.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderProductService {
    private final OrderProductRepository orderProductRepository;
    private final OrderService orderService;
    private final ProductRepository productRepository;

    @Transactional
    public void saveOrderProducts(List<ProductItem> products, Order order) {
        for(ProductItem pr : products){
            OrderProduct orderProduct = new OrderProduct();
            Optional<Product> productOptional = productRepository.findById(pr.getId());
            orderProduct.setAmount(pr.getAmount());
            orderProduct.setName(pr.getName());
            orderProduct.setTotalPrice(pr.getPrice()*pr.getAmount());
            orderProduct.setProduct(productOptional.get());
            orderProduct.setOrder(order);
            orderProductRepository.save(orderProduct);
        }
    }
}
