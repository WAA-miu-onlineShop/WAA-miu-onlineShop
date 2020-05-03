package com.miu.waa.groupbravo.onlineshop.domain;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

public class Review extends DomainClass {
    private String description;
    private LocalDateTime reviewDate;
    private User buyer;
    private Product product;
    @Enumerated(EnumType.STRING)
    private EReviewStatus reviewStatus;
}
