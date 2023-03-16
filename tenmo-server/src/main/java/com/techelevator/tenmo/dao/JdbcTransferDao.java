package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcTransferDao implements TransferDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String createTransfer(Transfer transfer) {
        if (transfer.getToAccountId() == transfer.getFromAccountId()) {
            return "Error! You cannot send money to yourself!";

        } else if (transfer.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            return "You have to send an amount greater than zero.";
        }
        String sql = "INSERT INTO transfer (to_account_id, from_account_id, amount, status_id) VALUES (?, ?, ?, ?);";
        jdbcTemplate.update(sql, transfer.getToAccountId(), transfer.getFromAccountId(), transfer.getAmount(), transfer.getTransferStatusId());

        return "Transfer Successful!";
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


    public Transfer mapRowToTransfer(SqlRowSet results) {
        Transfer transfer = new Transfer();

        int transfer_id = results.getInt("transfer_id");
        transfer.setTransferId(transfer_id);

        int transfer_type_id = results.getInt("transfer_type_id");
        transfer.setTransferTypeId(transfer_type_id);

        int transfer_status_id = results.getInt("transfer_status_id");
        transfer.setTransferStatusId(transfer_status_id);

        int from_account_id = results.getInt("from_account_id");
        transfer.setFromAccountId(from_account_id);

        int to_account_id = results.getInt("to_account_id");
        transfer.setToAccountId(to_account_id);

        BigDecimal amount = results.getBigDecimal("amount");
        transfer.setAmount(amount);

        return transfer;
    }
}