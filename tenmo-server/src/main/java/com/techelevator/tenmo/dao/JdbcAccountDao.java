package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcAccountDao implements AccountDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createAccount(Account account, int accountId) {
    }

    @Override
    public Account getAccount(int accountId) {

        return null;
    }

    @Override
    public Account getAccountByUserId(int userId) {

        return null;
    }

    @Override
    public BigDecimal getBalanceByAccountId(int accountId) {

        String sql = "SELECT balance FROM account WHERE account_id = ?;";
        SqlRowSet results = null;
        BigDecimal balance = jdbcTemplate.queryForObject(sql, BigDecimal.class, accountId);

        return balance;
<<<<<<< HEAD
        
=======
>>>>>>> 9ad3d75bc440b6a88be418b47e991f365484c500
    }

    @Override
    public BigDecimal getBalanceByUserId(int userId) {

        return null;
    }
}