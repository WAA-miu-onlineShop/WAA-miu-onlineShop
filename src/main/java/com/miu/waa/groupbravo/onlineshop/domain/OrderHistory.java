package com.miu.waa.groupbravo.onlineshop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class OrderHistory extends  DomainClass {
    private String description;
    private LocalDateTime historyDate;
   @OneToOne
    @JoinColumn(name="order_id")
    private Order order;
    @Enumerated(EnumType.STRING)
    private EOrderStatus orderStatus;
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getHistoryDate() {
        return historyDate;
    }

    public void setHistoryDate(LocalDateTime historyDate) {
        this.historyDate = historyDate;
    }

   public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public EOrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(EOrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        OrderHistory that = (OrderHistory) o;

        return new EqualsBuilder()
                .append(historyDate, that.historyDate)
                .append(order, that.order)
                .append(orderStatus, that.orderStatus)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(historyDate)
                .append(order)
                .append(orderStatus)
                .toHashCode();
    }*/
}
