package com.example.mad_kavindu.listeners;

public interface CustemerListClickListener {
    void onCustomerListAddToCartClicked(String rowId, String quantity);
    void onCustomerListOrderClicked(String rowId, String quantity);
}
