package com.wayakeji.payment;

import com.wayakeji.common.core.util.code.TextCoding;
import com.wayakeji.payment.common.Module;
import com.wayakeji.payment.common.Payment;
import com.wayakeji.payment.common.WxpayClient;
import com.wayakeji.payment.exception.ParameterParseException;
import com.wayakeji.payment.exception.PaymentRequestException;
import com.wayakeji.payment.exception.PaymentResponseException;
import com.wayakeji.payment.wxpay.DefaultWxpayConfig;
import com.wayakeji.payment.wxpay.pojo.Refund;

import java.io.File;
import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	public static void main(String[] args) throws ParameterParseException, PaymentRequestException, PaymentResponseException, IOException {
//		WxpayConfig config = new DefaultWxpayConfig("wx61ff5774c6690b84", "1498170212", "f80a57705d21fa54a402dd2ee53af85c");
//		WxpayClient client = Payment.buildClient(config);
//		Module<UnifiedOrderApp> module = client.createUnifiedOrderAppModule();
//		UnifiedOrderApp param = new UnifiedOrderApp("测试支付", 1, "192.168.0.1", "http://192.168.0.1");
//		module.parameter(param);
//		JSONObject json = module.execute();
//		System.out.println(json);
		DefaultWxpayConfig config = new DefaultWxpayConfig("wxd6176a6cec66044c", "1523132971", "0fe695ad635cd1938459cacc74b102b5");
		config.setCertStream(TextCoding.strSplice(
				System.getProperty("user.dir"),
				File.separator,
				"p12",
				File.separator,
				"apiclient_cert.p12"));
		WxpayClient client = Payment.buildClient(config);
		Module<Refund> module = client.createRefundModule();
		Refund refund = new Refund("15769006620741701509", 1500, 1500);
		module.parameter(refund);
		System.out.println(module.execute());
//		oTCIw5dTQidoQeDMv29A__ludCTk
	}
	
}