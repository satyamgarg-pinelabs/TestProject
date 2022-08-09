package com.test.testdrools.entities;

import com.test.testdrools.enums.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private CustomerType type;
    private int years;
    private int discount;

}
