package com.wayakeji.payment.pojo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * <p>微信支付结果异步通知
 * <p>详见<a href="https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_7&index=3">支付宝官方文档</a>
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @since 1.8
 * @version 1.0
 * @time 2019年7月24日
 */
public class WxpayNotify {
	
	@JSONField(name="appid")
	private String appId;
	@JSONField(name="mch_id")
	private String mchId;
	@JSONField(name="device_info")
	private String deviceInfo;
	@JSONField(name="nonce_str")
	private String nonceStr;
	private String sign;
	@JSONField(name="sign_type")
	private String signType;
	@JSONField(name="result_code")
	private String resultCode;
	@JSONField(name="err_code")
	private String errCode;
	@JSONField(name="err_code_des")
	private String errCodeDes;
	@JSONField(name="openid")
	private String openId;
	@JSONField(name="is_subscribe")
	private String subscribe;
	@JSONField(name="trade_type")
	private String tradeType;
	@JSONField(name="bank_type")
	private String bankType;
	@JSONField(name="total_fee")
	private Integer totalFee;
	@JSONField(name="settlement_total_fee")
	private Integer settlementTotalFee;
	@JSONField(name="fee_type")
	private String feeType;
	@JSONField(name="cash_fee")
	private Integer cashFee;
	@JSONField(name="cash_fee_type")
	private String cashFeeType;
	@JSONField(name="coupon_fee")
	private Integer couponFee;
	@JSONField(name="coupon_count")
	private Integer couponCount;
	@JSONField(name="coupon_type_$n")
	private String couponTypeN;
	@JSONField(name="coupon_id_$n")
	private String couponIdN;
	@JSONField(name="coupon_fee_$n")
	private Integer couponFeeN;
	@JSONField(name="transaction_id")
	private String transactionId;
	@JSONField(name="out_trade_no")
	private String outTradeNo;
	private String attach;
	@JSONField(name="return_code")
	private String returnCode;
	@JSONField(name="return_msg")
	private String returnMsg;
	@JSONField(name="time_end")
	private String timeEnd;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
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
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrCodeDes() {
		return errCodeDes;
	}
	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTrade_type(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public Integer getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}
	public Integer getSettlementTotalFee() {
		return settlementTotalFee;
	}
	public void setSettlementTotalFee(Integer settlementTotalFee) {
		this.settlementTotalFee = settlementTotalFee;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public Integer getCashFee() {
		return cashFee;
	}
	public void setCashFee(Integer cashFee) {
		this.cashFee = cashFee;
	}
	public String getCashFeeType() {
		return cashFeeType;
	}
	public void setCashFeeType(String cashFeeType) {
		this.cashFeeType = cashFeeType;
	}
	public Integer getCouponFee() {
		return couponFee;
	}
	public void setCouponFee(Integer couponFee) {
		this.couponFee = couponFee;
	}
	public Integer getCouponCount() {
		return couponCount;
	}
	public void setCouponCount(Integer couponCount) {
		this.couponCount = couponCount;
	}
	public String getCouponTypeN() {
		return couponTypeN;
	}
	public void setCouponTypeN(String couponTypeN) {
		this.couponTypeN = couponTypeN;
	}
	public String getCouponIdN() {
		return couponIdN;
	}
	public void setCouponIdN(String couponIdN) {
		this.couponIdN = couponIdN;
	}
	public Integer getCouponFeeN() {
		return couponFeeN;
	}
	public void setCouponFeeN(Integer couponFeeN) {
		this.couponFeeN = couponFeeN;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMsg() {
		return returnMsg;
	}
	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	@Override
	public String toString() {
		return "WxpayNotify{" +
				"appId='" + appId + '\'' +
				", mchId='" + mchId + '\'' +
				", deviceInfo='" + deviceInfo + '\'' +
				", nonceStr='" + nonceStr + '\'' +
				", sign='" + sign + '\'' +
				", signType='" + signType + '\'' +
				", resultCode='" + resultCode + '\'' +
				", errCode='" + errCode + '\'' +
				", errCodeDes='" + errCodeDes + '\'' +
				", openId='" + openId + '\'' +
				", subscribe='" + subscribe + '\'' +
				", tradeType='" + tradeType + '\'' +
				", bankType='" + bankType + '\'' +
				", totalFee=" + totalFee +
				", settlementTotalFee=" + settlementTotalFee +
				", feeType='" + feeType + '\'' +
				", cashFee=" + cashFee +
				", cashFeeType='" + cashFeeType + '\'' +
				", couponFee=" + couponFee +
				", couponCount=" + couponCount +
				", couponTypeN='" + couponTypeN + '\'' +
				", couponIdN='" + couponIdN + '\'' +
				", couponFeeN=" + couponFeeN +
				", transactionId='" + transactionId + '\'' +
				", outTradeNo='" + outTradeNo + '\'' +
				", attach='" + attach + '\'' +
				", returnCode='" + returnCode + '\'' +
				", returnMsg='" + returnMsg + '\'' +
				", timeEnd='" + timeEnd + '\'' +
				'}';
	}
}
