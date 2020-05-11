package com.miu.waa.groupbravo.onlineshop.domain.message;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@EqualsAndHashCode
@Getter
@Setter
@Entity
public class UserMessage implements Serializable {
    private static final long serialVersionUID = 111L;
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String Value;
}
