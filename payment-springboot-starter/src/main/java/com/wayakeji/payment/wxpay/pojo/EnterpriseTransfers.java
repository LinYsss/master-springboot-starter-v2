package com.wayakeji.payment.wxpay.pojo;

import com.wayakeji.payment.annotation.PayField;
import com.wayakeji.payment.common.CommonPojo;

/**
 * 企业转账到个人用户参数类
 * @author hu trace
 */
public class EnterpriseTransfers extends CommonPojo {

	@PayField("device_info")
	protected  String deviceInfo;

	@PayField("nonce_str")
	protected  String nonceStr;

	@PayField("partner_trade_no")
	protected  String partnerTradeNo;

	@PayField("openid")
	protected  String openId;

	@PayField("check_name")
	protected  CheckName checkName = CheckName.NO_CHECK;

	@PayField("re_user_name")
	protected  String reUserName;

	@PayField("amount")
	protected  int amount;

	@PayField("desc")
	protected  String desc;

	@PayField("spbill_create_ip")
	protected  String spbillCreateIp = "127.0.0.1";
	
	public enum CheckName {
		/** 不校验真实姓名 */
		NO_CHECK,
		/** 强校验真实姓名 */
		FORCE_CHECK
	}
	
	/**
	 * 最基本的参数构造方法，只包含了必要参数
	 * @param openId 公众号用户openId
	 * @param amount 转账金额，单位分
	 * @param desc 转账备注
	 */
	public EnterpriseTransfers(String openId, int amount, String desc) {
		this.openId = openId;
		this.amount = amount;
		this.desc = desc;
		this.nonceStr = super.nonceStr();
		this.partnerTradeNo = super.outTradeNo();
	}
	
	/**
	 * 最基本的参数构造方法，只包含了必要参数
	 * @param openId 公众号用户openId
	 * @param amount 转账金额，单位分
	 * @param desc 转账备注
	 * @param reUserName 收款用户真实姓名
	 */
	public EnterpriseTransfers(String openId, int amount, String desc, String reUserName) {
		this.openId = openId;
		this.amount = amount;
		this.desc = desc;
		this.reUserName = reUserName;
		this.checkName = CheckName.FORCE_CHECK;
	}
	
	/**
	 * 最基本的参数构造方法，只包含了必要参数
	 * @param openId 公众号用户openId
	 * @param amount 转账金额，单位分
	 * @param desc 转账备注
	 * @param nonceStr 随机字符串
	 * @param partnerTradeNo 转账订单号
	 */
	public EnterpriseTransfers(String openId, int amount, String desc, String nonceStr, String partnerTradeNo) {
		this.openId = openId;
		this.amount = amount;
		this.desc = desc;
		this.nonceStr = nonceStr;
		this.partnerTradeNo = partnerTradeNo;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public CheckName getCheckName() {
		return checkName;
	}

	public void setCheckName(CheckName checkName) {
		this.checkName = checkName;
	}

	public String getReUserName() {
		return reUserName;
	}

	public void setReUserName(String reUserName) {
		this.reUserName = reUserName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}

	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}
	
}
