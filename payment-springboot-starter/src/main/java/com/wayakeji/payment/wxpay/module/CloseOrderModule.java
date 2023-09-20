package com.wayakeji.payment.wxpay.module;

import com.wayakeji.payment.wxpay.DefaultWxpayModule;
import com.wayakeji.payment.wxpay.WxpayConfig;
import com.wayakeji.payment.wxpay.pojo.CloseOrder;

/**
 * <p>微信支付关闭订单模块
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see DefaultWxpayModule
 * @see CloseOrder
 * @since 1.8
 * @version 1.0
 * @time 2019年9月28日
 */
public class CloseOrderModule extends DefaultWxpayModule<CloseOrder> {

	public CloseOrderModule(WxpayConfig config) {
		super(config);
	}

	@Override
	public String path() {
		return "/pay/closeorder";
	}

}
