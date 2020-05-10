package com.miu.waa.groupbravo.onlineshop.domain;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
public class Following extends  DomainClass {
    private Boolean follow= Boolean.TRUE;
    private LocalDate date;
    @OneToOne
    @JoinColumn(name="seller_id")
    private User seller;
    @OneToOne
    @JoinColumn(name="buyer_id")
    private User buyer;

    public Boolean getFollow() {
        return follow;
    }

    public void setFollow(Boolean follow) {
        this.follow = follow;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Following following = (Following) o;

        return new EqualsBuilder()
                .append(seller, following.seller)
                .append(buyer, following.buyer)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(seller)
                .append(buyer)
                .toHashCode();
    }
}
