package com.aritha.bankingapp.repository;

import com.aritha.bankingapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
