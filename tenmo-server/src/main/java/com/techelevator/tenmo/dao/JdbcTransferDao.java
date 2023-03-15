package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcTransferDao implements TransferDao, AccountDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createTransfer(Transfer transfer) {
        String sql = "INSERT INTO transfer (to_account_id, from_account_id, amount, status_id) VALUES (?, ?, ?, ?);";
        jdbcTemplate.update(sql, transfer.getToAccountId(), transfer.getFromAccountId(), transfer.getAmount(), transfer.getStatus_id());
    }

    @Override
    public Transfer getTransfer(int transferId) {
        return null;
    }

    @Override
    public Transfer getTransferByAccountId(int account_id) {
        return null;
    }

    @Override
    public int getTransferStatus(int transfer_id) {
        return 0;
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
        return null;
    }

    @Override
    public BigDecimal getBalanceByUserId(int userId) {
        return null;
    }
}
