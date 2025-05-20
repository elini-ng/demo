package com.estsoft.demo.mock;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.*;

public class UserCouponTest {
    @Test
    public void testUserCoupon() {
        //1. Create user, 2. Current coupon amount
        User user = new User("id");
        assertThat(user.countTotalCoupon()).isEqualTo(0);

        //3. Redeem a new coupon
        ICoupon coupon = new DummyCoupon();
        user.addCoupon(coupon);

        //4. Coupon count after redeem
        assertThat(user.countTotalCoupon()).isEqualTo(1);
    }

    //Use mock to handle fake object
    @Test
    public void testUserCouponWithMock() {
        User user = new User("area00");
        assertThat(user.countTotalCoupon()).isEqualTo(0);

        ICoupon coupon = Mockito.mock(ICoupon.class);
        Mockito.when(coupon.isValid())
                .thenReturn(true);

        user.addCoupon(coupon);
        assertThat(user.countTotalCoupon()).isEqualTo(1);
    }
}
