package com.estsoft.demo.mock;

public class DummyCoupon implements ICoupon{
    @Override
    public String getName() {
        return "";
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public int getDiscountPercent() {
        return 0;
    }

    @Override
    public boolean isApplicable(Item item) {
        return false;
    }

    @Override
    public void doExpire() {

    }
}
