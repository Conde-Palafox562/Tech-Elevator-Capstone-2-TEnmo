package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;

public interface TransferDao {
    public Transfer getTransfer(int transferId);
    public Transfer getTransferByAccountId(int account_id);
    public int getTransferStatus(int transfer_id);
    public String createTransfer(int toAccountId, int fromAccountId, BigDecimal amount, BigDecimal currentBalance);
}
