package my.examples.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import my.examples.shoppingmall.domain.Order;
import my.examples.shoppingmall.domain.Product;
import my.examples.shoppingmall.dto.RecordItem;
import my.examples.shoppingmall.repository.OrderRepository;
import my.examples.shoppingmall.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional(readOnly=true)
    public String generateOrderNumber() {
        String result = null;
        Date from = new Date();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String to = transFormat.format(from);
        result = "S"+to+UUID.randomUUID().toString();
        return result;
    }

    @Transactional(readOnly = false)
    public Order saveOrder(Order order) {
        orderRepository.save(order);
        return order;
    }

    @Transactional(readOnly = true)
    public List<Order> findRecordByGuest(RecordItem recordItem) {
        return orderRepository.findRecordByGuest(recordItem.getUserName(),
                                                 recordItem.getEmail(),recordItem.getPhone());
    }

    public List<Order> findMyOrderList(String email) {
        return orderRepository.findMyOrderList(email);
    }
}
