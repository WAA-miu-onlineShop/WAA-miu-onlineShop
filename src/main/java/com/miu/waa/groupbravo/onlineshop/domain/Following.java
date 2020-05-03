package com.miu.waa.groupbravo.onlineshop.domain;

import java.time.LocalDate;

public class Following extends  DomainClass {
    private Boolean follow=Boolean.TRUE;
    private LocalDate date;
    private User seller;
    private User buyer;
}
