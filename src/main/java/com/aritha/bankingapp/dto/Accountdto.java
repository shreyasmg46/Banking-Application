package com.aritha.bankingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Accountdto {

    private long id;
    private String account_holder_name;
    private double balance;
}
