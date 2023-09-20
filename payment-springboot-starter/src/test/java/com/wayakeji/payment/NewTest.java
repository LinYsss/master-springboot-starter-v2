package com.wayakeji.payment;

import com.alibaba.fastjson.JSONObject;
import com.wayakeji.common.core.util.code.TextCoding;
import com.wayakeji.payment.common.Module;
import com.wayakeji.payment.common.Payment;
import com.wayakeji.payment.common.WxpayClient;
import com.wayakeji.payment.wxpay.DefaultWxpayConfig;
import com.wayakeji.payment.wxpay.pojo.EnterpriseTransfers;
import com.wayakeji.payment.wxpay.pojo.EnterpriseTransfersQuery;

import java.io.File;

public class NewTest {
	
	public static void main(String[] args) throws Exception {
		DefaultWxpayConfig config = new DefaultWxpayConfig("wxff549fd76c357524", "1571374591", "TasBsrQ58M3RKBJ5G3bX83rPqBkNDuqH");
		config.setCertStream(TextCoding.strSplice(
				System.getProperty("user.dir"),
				File.separator,
				"p12",
				File.separator,
				"wa_dian_lai_le.p12"));
		WxpayClient client = Payment.buildClient(config);
		Module<EnterpriseTransfers> module = client.createEnterpriseTransfersModule();
		EnterpriseTransfers et = new EnterpriseTransfers("o97BU63XsksxeB_AN0VqFYvlvgW0", 100, "测试转账到个人账户");
		module.parameter(et);
		JSONObject json = module.execute();
		System.out.println(et.getPartnerTradeNo());
		System.out.println(json);
		
		Module<EnterpriseTransfersQuery> query = client.createEnterpriseTransfersQueryModule();
		query.parameter(new EnterpriseTransfersQuery(et.getPartnerTradeNo()));
		json = query.execute();
		System.out.println(json);
	}
	
}
