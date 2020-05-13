package com.miu.waa.groupbravo.onlineshop.domain;

import com.miu.waa.groupbravo.onlineshop.repository.OrderRepository;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
@NamedQueries({@NamedQuery(name=OrderRepository.QUERY_NAME.findBySellerAndStatus,query =OrderRepository.QUERY.findBySellerAndStatus ),
        @NamedQuery(name=OrderRepository.QUERY_NAME.findOrderByBuyerAndStatus,query=OrderRepository.QUERY.findOrderByBuyerAndStatus)})//
@Entity(name="Orders")
public class Order extends DomainClass {
    private String orderNumber;
    private BigDecimal totalAmount= BigDecimal.ZERO;
    private BigDecimal coupons= BigDecimal.ZERO;
    @Enumerated(EnumType.STRING)
    private EOrderStatus orderStatus;
    @ManyToOne
    @JoinColumn(name="buyer_id")
    private User buyer;
   @OneToOne
    @JoinColumn(name="shippingAddress_id")
    private Address shippingAddress;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderLine> orderLineList;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getTotalAmount() {



        return orderLineList.stream().map(x->x.getAmount()).reduce(BigDecimal.ZERO,(x,y)->x.add(y));
    }

    public void setTotalAmount(BigDecimal totalAmount) {



        this.totalAmount = totalAmount;
    }

    public BigDecimal getCoupons() {
        return coupons;
    }

    public void setCoupons(BigDecimal coupons) {
        this.coupons = coupons;
    }

    public EOrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(EOrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

   public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

       public List<OrderLine> getOrderLineList() {
           return orderLineList;
       }

       public void setOrderLineList(List<OrderLine> orderLineList) {
           this.orderLineList = orderLineList;
       }
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(orderNumber).toHashCode();
    }
    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Order))
            return false;
        Order other = (Order) obj;
        return new EqualsBuilder().append(orderNumber, other.orderNumber).isEquals();

    }
}
