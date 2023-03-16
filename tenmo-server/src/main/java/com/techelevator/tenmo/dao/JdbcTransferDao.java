package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {

    private String initialStatus = "Approved";

    private String transferRequest = "Request";

    private String transferSend = "Payment";

    private final JdbcTemplate jdbcTemplate;

    AccountDao accountDao = new AccountDao;

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
        String sql = "INSERT INTO transfer (to_account_id, from_account_id, amount, transfer_status_id) VALUES (?, ?, ?, ?);";
        jdbcTemplate.update(sql, transfer.getToAccountId(), transfer.getFromAccountId(), transfer.getAmount(), transfer.getTransferStatusId());

        String sql1 = "Insert INTO transfer_status (transfer_status_desc) VALUES (?);";
        jdbcTemplate.update(sql1, initialStatus);

        String sql2 = "INSERT INTO transfer_type (transfer_type_desc) VALUES (?);";
        jdbcTemplate.update(sql2, transferSend);

        accountDao.addToBalance(transfer.getAmount(), transfer.getToAccountId());

        accountDao.subtractFromBalance(transfer.getAmount(), transfer.getFromAccountId());

        return "Transfer Successful!";
    }

    @Override
    public Transfer getTransfer(int transferId) {

        Transfer transfer = null;
        String sql = "SELECT * FROM transfer WHERE transfer_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transferId);
        transfer = mapRowToTransfer(result);
        return transfer;

    }

    @Override
    public Transfer getTransferByAccountId(int account_id) {
        return null;
    }

    @Override
    public int getTransferStatus(int transfer_id) {
        return 0;
    }

    public List<Transfer> getTransferListByAccountId(int userId){

        List<Transfer> transferList = new ArrayList<>();
        String sql = "SELECT * FROM transfer WHERE to_account_id = ? OR from_account_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, userId);
        while(results.next()){
            Transfer transfer = mapRowToTransfer(results);
            transferList.add(transfer);
        }
        return transferList;
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