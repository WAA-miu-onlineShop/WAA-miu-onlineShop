package com.miu.waa.groupbravo.onlineshop.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
public class Review extends DomainClass {
    private String description;
    private LocalDateTime reviewDate;
    @OneToOne
    private User buyer;
    @OneToOne
    @JoinColumn(name="product_id")
    private Product product;
    @Enumerated(EnumType.STRING)
    private EReviewStatus reviewStatus;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public EReviewStatus getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(EReviewStatus reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        return new EqualsBuilder()
                .append(buyer, review.buyer)
                .append(product, review.product)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(buyer)
                .append(product)
                .toHashCode();
    }
}
