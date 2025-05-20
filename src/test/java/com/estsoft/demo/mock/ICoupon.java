package com.estsoft.demo.mock;

public interface ICoupon {
    String getName();
    boolean isValid();
    int getDiscountPercent();
    boolean isApplicable(Item item);
    void doExpire();
}
