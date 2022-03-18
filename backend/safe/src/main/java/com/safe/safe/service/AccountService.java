package com.safe.safe.service;

import com.safe.safe.dto.AccountDTO;
import com.safe.safe.entity.Account;
import com.safe.safe.exception.AccountNotFoundException;
import com.safe.safe.helper.AccountHelper;
import com.safe.safe.repository.AccountRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountHelper accountHelper;

    public AccountService(AccountRepository accountRepository, AccountHelper accountHelper) {
        this.accountRepository = accountRepository;
        this.accountHelper = accountHelper;
    }

    public List<AccountDTO> findAllAccount() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(AccountDTO::new)
                .collect(Collectors.toList());
    }

    public AccountDTO findAccountById(Long id){
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account by id "+id+" not found"));
        return new AccountDTO(account);
    }

    public AccountDTO saveAccount(AccountDTO newAccount) {
        Account account = new Account();
        accountHelper.convertDTOtoEntity(account, newAccount);
        account = accountRepository.save(account);
        return new AccountDTO(account);
    }

    public AccountDTO updateAccount(Long id, AccountDTO newDataAccount) {
        try {
            Optional<Account> accountEntity = accountRepository.findById(id);
            Account entity = accountEntity.orElseThrow(() -> new AccountNotFoundException("ID Account not found: "+id));
            accountHelper.convertDTOtoEntity(entity, newDataAccount);
            entity = accountRepository.save(entity);
            return new AccountDTO(entity);
        }catch (EntityNotFoundException e){
            throw new AccountNotFoundException("ID Account not found: "+id);
        }
    }

    public void deleteAccount(Long id) {
        try{
            accountRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new AccountNotFoundException("ID Account not found: "+id);
        }
    }
}
