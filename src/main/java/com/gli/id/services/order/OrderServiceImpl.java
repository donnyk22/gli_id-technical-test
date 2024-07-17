package com.gli.id.services.order;

import com.gli.id.dtos.OrderDto;
import com.gli.id.dtos.exceptions.BusinessException;
import com.gli.id.dtos.forms.OrderAddForm;
import com.gli.id.dtos.forms.OrderDetailAddForm;
import com.gli.id.dtos.forms.OrderDetailEditForm;
import com.gli.id.dtos.forms.OrderEditForm;
import com.gli.id.dtos.mappers.OrderMapper;
import com.gli.id.models.*;
import com.gli.id.repositories.OrderDetailRepository;
import com.gli.id.repositories.OrderRepository;
import com.gli.id.repositories.ProductRepository;
import com.gli.id.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<OrderDto> find(Integer id) {
        if (id == null){
            Iterable<Order> orders = orderRepository.findAll();
            if (!orders.iterator().hasNext()) throw new BusinessException("Order data is empty");
            return StreamSupport
                    .stream(orders.spliterator(), false)
                    .map(OrderMapper::toBaseDto)
                    .collect(Collectors.toList());
        }

        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) throw new BusinessException("Order data not found");
        return new ArrayList<>(List.of(OrderMapper.toDetailDto(order)));
    }

    @Override
    public OrderDto add(OrderAddForm form) {
        Order order = new Order();
        User user = userRepository.findById(form.getUser_id()).orElse(null);
        if (user == null) throw new BusinessException("User data is not found");
        order.setUser_id(form.getUser_id())
                .setTotal_price(0)
                .setUser_detail(user);
        orderRepository.save(order);

        int totalPrice = 0;
        List<OrderDetail> orderDetails = null;

        if (form.getOrder_list() != null && !form.getOrder_list().isEmpty()){
            orderDetails = new ArrayList<>();
            for (OrderDetailAddForm orderDetailAddForm : form.getOrder_list()) {
                Product product = productRepository.findById(orderDetailAddForm.getProduct_id()).orElse(null);
                if (product == null) throw new BusinessException("Product data is not found");

                OrderDetail orderDetail = new OrderDetail()
                        .setOrder_id(order.getId())
                        .setProduct_id(orderDetailAddForm.getProduct_id())
                        .setQty(orderDetailAddForm.getQty())
                        .setProduct_detail(product);

                orderDetailRepository.save(orderDetail);

                orderDetails.add(orderDetail);
                totalPrice += product.getPrice() * orderDetailAddForm.getQty();
            }
        }

        order.setTotal_price(totalPrice);
        order.setOrder_list(orderDetails);
        orderRepository.save(order);

        return OrderMapper.toDetailDto(order);
    }

    @Override
    public OrderDto edit(OrderEditForm form) {
        Order order = orderRepository.findById(form.getId()).orElse(null);
        if (order == null) throw new BusinessException("Order data is not found");

        User user = userRepository.findById(form.getUser_id()).orElse(null);
        if (user == null) throw new BusinessException("User data is not found");
        order.setUser_id(form.getUser_id());

        int totalPrice = 0;
        List<OrderDetail> orderDetails = null;

        if (form.getOrder_list() != null && !form.getOrder_list().isEmpty()){
            orderDetails = new ArrayList<>();
            List<Integer> orderDetailEditedIds = new ArrayList<>();

            for (OrderDetailEditForm orderDetailEditForm : form.getOrder_list()){
                Product product = productRepository.findById(orderDetailEditForm.getProduct_id()).orElse(null);
                if (product == null) throw new BusinessException("Product data is not found");

                OrderDetail orderDetail = new OrderDetail()
                        .setOrder_id(form.getId())
                        .setProduct_id(orderDetailEditForm.getProduct_id())
                        .setQty(orderDetailEditForm.getQty())
                        .setProduct_detail(product);

                if (orderDetailEditForm.getId() != null) orderDetail.setId(orderDetailEditForm.getId());

                orderDetailRepository.save(orderDetail);

                orderDetails.add(orderDetail);
                orderDetailEditedIds.add(orderDetail.getId());
                totalPrice += product.getPrice() * orderDetailEditForm.getQty();
            }

            if (!orderDetailEditedIds.isEmpty()) orderDetailRepository.deleteFilteredId(orderDetailEditedIds, form.getId());

        }else{
            orderDetailRepository.deleteAllByOrder_id(form.getId());
        }

        order.setTotal_price(totalPrice);
        orderRepository.save(order);

        order.setUser_detail(user);
        order.setOrder_list(orderDetails);
        return OrderMapper.toDetailDto(order);
    }

    @Override
    public OrderDto delete(Integer id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) throw new BusinessException("Order data is not found");
        order.setDeleted(true);
        orderRepository.save(order);

        return OrderMapper.toDetailDto(order);
    }
}
