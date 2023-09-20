package com.wayakeji.payment.wxpay.module;

import com.wayakeji.payment.common.CommonPojo;
import com.wayakeji.payment.exception.ParameterParseException;
import com.wayakeji.payment.wxpay.DefaultWxpayModule;
import com.wayakeji.payment.wxpay.WxpayConfig;
import com.wayakeji.payment.wxpay.pojo.EnterpriseTransfers;

import java.util.Map;

/**
 * 企业转账到个人用户实现模块
 * @author hu trace
 */
public class EnterpriseTransfersModule extends DefaultWxpayModule<EnterpriseTransfers> {

	public EnterpriseTransfersModule(WxpayConfig config) {
		super(config);
	}

	@Override
	public String path() {
		return "/mmpaymkttransfers/promotion/transfers";
	}
	
	@Override
	public void parameter(CommonPojo param) throws ParameterParseException {
		Map<String, Object> map = param.toMap();
		map.put("mch_appid", config.appId());
		map.put("mchid", config.mchId());
		this.parameter(map);
	}
	
	@Override
	public boolean useCert() {
		return true;
	}

}
