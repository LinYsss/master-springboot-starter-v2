package com.wayakeji.payment.wxpay.module;

import com.wayakeji.payment.wxpay.DefaultWxpayModule;
import com.wayakeji.payment.wxpay.WxpayConfig;
import com.wayakeji.payment.wxpay.pojo.Refund;

/**
 * <p>微信退款模块
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see DefaultWxpayModule
 * @see Refund
 * @since 1.8
 * @version 1.0
 * @time 2019年9月28日
 */
public class RefundModule extends DefaultWxpayModule<Refund> {

	public RefundModule(WxpayConfig config) {
		super(config);
	}

	@Override
	public String path() {
		return "/secapi/pay/refund";
	}
	
	@Override
	public boolean useCert() {
		return true;
	}

}
