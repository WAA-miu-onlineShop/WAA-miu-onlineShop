package com.miu.waa.groupbravo.onlineshop.domain;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Coupon  extends DomainClass implements Serializable {
    private String couponNumber;
    @OneToOne
    @JoinColumn(name="buyer_id")
    private User buyer;
    private BigDecimal totalPoint= BigDecimal.ZERO;

    public String getCouponNumber() {
        return couponNumber;
    }

    public void setCouponNumber(String couponNumber) {
        this.couponNumber = couponNumber;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public BigDecimal getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(BigDecimal totalPoint) {
        this.totalPoint = totalPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Coupon coupon = (Coupon) o;

        return new EqualsBuilder()
                .append(couponNumber, coupon.couponNumber)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(couponNumber)
                .toHashCode();
    }
}
