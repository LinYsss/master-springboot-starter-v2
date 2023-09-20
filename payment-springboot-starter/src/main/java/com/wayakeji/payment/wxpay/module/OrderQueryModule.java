package com.wayakeji.payment.wxpay.module;

import com.wayakeji.payment.common.AbstractModule;
import com.wayakeji.payment.wxpay.DefaultWxpayModule;
import com.wayakeji.payment.wxpay.WxpayConfig;
import com.wayakeji.payment.wxpay.pojo.OrderQuery;

/**
 * <p>微信支付订单查询模块
 * <p>使用此模块即可完成app统一下单
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see AbstractModule
 * @see OrderQuery
 * @since 1.8
 * @version 1.0
 * @time 2019年7月23日
 */
public class OrderQueryModule extends DefaultWxpayModule<OrderQuery> {

	public OrderQueryModule(WxpayConfig config) {
		super(config);
	}

	@Override
	public String path() {
		return "/pay/orderquery";
	}

}
