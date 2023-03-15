package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

public interface TransferDao {

    public String createTransfer(Transfer transfer);
    public Transfer getTransfer(int transferId);
    public Transfer getTransferByAccountId(int account_id);
    public int getTransferStatus(int transfer_id);
}
