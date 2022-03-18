package com.safe.safe.dto;

import com.safe.safe.entity.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AccountDTO implements Serializable {
    private Long id;
    private String name;
    private Double balance;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.name = account.getName();
        this.balance = account.getBalance();
    }
}
