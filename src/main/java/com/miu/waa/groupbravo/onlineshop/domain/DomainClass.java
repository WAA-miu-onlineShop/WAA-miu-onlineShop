package com.miu.waa.groupbravo.onlineshop.domain;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class DomainClass  implements Serializable {
    @NotNull
    private Long id;
    private int  version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
