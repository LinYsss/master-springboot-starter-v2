package com.wayakeji.payment.wxpay.module;

import com.wayakeji.payment.common.CommonPojo;
import com.wayakeji.payment.exception.ParameterParseException;
import com.wayakeji.payment.wxpay.DefaultWxpayModule;
import com.wayakeji.payment.wxpay.WxpayConfig;
import com.wayakeji.payment.wxpay.pojo.EnterpriseTransfersQuery;

import java.util.Map;

/**
 * 查询企业转账到个人用户模块
 * @author hu trace
 */
public class EnterpriseTransfersQueryModule extends DefaultWxpayModule<EnterpriseTransfersQuery> {

	public EnterpriseTransfersQueryModule(WxpayConfig config) {
		super(config);
	}

	@Override
	public String path() {
		return "/mmpaymkttransfers/gettransferinfo";
	}
	
	@Override
	public void parameter(CommonPojo param) throws ParameterParseException {
		Map<String, Object> map = param.toMap();
		map.put("appid", config.appId());
		map.put("mch_id", config.mchId());
		this.parameter(map);
	}
	
	@Override
	public boolean useCert() {
		return true;
	}

}
