package com.wayakeji.payment.wxpay.pojo;

import com.wayakeji.payment.annotation.PayField;
import com.wayakeji.payment.common.CommonPojo;

public class EnterpriseTransfersQuery extends CommonPojo {
	
	@PayField("nonce_str")
	protected  String nonceStr;
	
	@PayField("partner_trade_no")
	protected  String partnerTradeNo;
	
	public EnterpriseTransfersQuery(String partnerTradeNo) {
		this.partnerTradeNo = partnerTradeNo;
		this.nonceStr = super.nonceStr();
	}
	
	public EnterpriseTransfersQuery(String partnerTradeNo, String nonceStr) {
		this.partnerTradeNo = partnerTradeNo;
		this.nonceStr = nonceStr;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPartnerTradeNo() {
		return partnerTradeNo;
	}

	public void setPartnerTradeNo(String partnerTradeNo) {
		this.partnerTradeNo = partnerTradeNo;
	}
	
}
