package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcTransferDao implements TransferDao{

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
}
