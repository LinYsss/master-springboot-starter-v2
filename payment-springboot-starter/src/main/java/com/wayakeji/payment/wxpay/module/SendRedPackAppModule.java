package com.wayakeji.payment.wxpay.module;

import com.wayakeji.payment.common.CommonPojo;
import com.wayakeji.payment.exception.ParameterParseException;
import com.wayakeji.payment.wxpay.DefaultWxpayModule;
import com.wayakeji.payment.wxpay.WxpayConfig;
import com.wayakeji.payment.wxpay.pojo.SendRedPack;
import com.wayakeji.payment.wxpay.pojo.SendRedPackApp;

import java.util.Map;

/**
 * <p>微信小程序发送红包的接口
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see DefaultWxpayModule
 * @see SendRedPack
 * @since 1.8
 * @version 1.0
 * @time 2019年9月10日
 */
public class SendRedPackAppModule extends DefaultWxpayModule<SendRedPackApp> {

	public SendRedPackAppModule(WxpayConfig config) {
		super(config);
	}
	
	@Override
	public boolean useCert() {
		return true;
	}
	
	@Override
	public void parameter(CommonPojo param) throws ParameterParseException {
		Map<String, Object> map = param.toMap();
		map.put("wxappid", config.appId());
		map.put("mch_id", config.mchId());
		this.parameter(map);
	}

	@Override
	public String path() {
		return "/mmpaymkttransfers/sendminiprogramhb";
	}
	
}
