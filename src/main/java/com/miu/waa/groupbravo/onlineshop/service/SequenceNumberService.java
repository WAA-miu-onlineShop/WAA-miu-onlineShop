package com.miu.waa.groupbravo.onlineshop.service;

import com.miu.waa.groupbravo.onlineshop.domain.ERoleType;
import com.miu.waa.groupbravo.onlineshop.domain.EUserStatus;

public interface SequenceNumberService {
    public String getNextUserNumber(ERoleType roleType);
    public String getNextOrderNumber();
    public String getNextProductNumber();
    public String getNextPaymentNumber();

}
