package com.wayakeji.payment.wxpay.module;

import com.wayakeji.payment.common.AbstractModule;
import com.wayakeji.payment.wxpay.DefaultWxpayModule;
import com.wayakeji.payment.wxpay.WxpayConfig;
import com.wayakeji.payment.wxpay.pojo.UnifiedOrder;

/**
 * <p>微信支付统一下单模块
 * <p>使用此模块即可完成app统一下单
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see AbstractModule
 * @see UnifiedOrder
 * @since 1.8
 * @version 1.0
 * @time 2019年7月23日
 */
public class UnifiedOrderModule extends DefaultWxpayModule<UnifiedOrder> {

	public UnifiedOrderModule(WxpayConfig config) {
		super(config);
	}

	@Override
	public String path() {
		return "/pay/unifiedorder";
	}
	
}
