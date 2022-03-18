package com.safe.safe.helper;

import com.safe.safe.dto.AccountDTO;
import com.safe.safe.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountHelper {
    public void convertDTOtoEntity(Account entity, AccountDTO dto) {
        entity.setName(dto.getName());
        entity.setBalance(dto.getBalance());
    }

}
