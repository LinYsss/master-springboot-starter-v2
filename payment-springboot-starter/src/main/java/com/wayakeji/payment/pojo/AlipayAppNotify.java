package com.wayakeji.payment.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

/**
 * <p>支付宝APP支付结果异步通知
 * <p>详见<a href="https://docs.open.alipay.com/204/105301/">支付宝官方文档</a>
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @since 1.8
 * @version 1.0
 * @time 2019年7月24日
 */
public class AlipayAppNotify {
	
	private BigDecimal scale = new BigDecimal(100);
	
	@JSONField(name="notify_time")
	private String notifyTime;
	@JSONField(name="notify_type")
	private String notifyType;
	@JSONField(name="notify_id")
	private String notifyId;
	@JSONField(name="app_id")
	private String appId;
	private String charset;
	private String version;
	@JSONField(name="sign_type")
	private String signType;
	private String sign;
	@JSONField(name="trade_no")
	private String tradeNo;
	@JSONField(name="out_trade_no")
	private String outTradeNo;
	@JSONField(name="out_biz_no")
	private String outBizNo;
	@JSONField(name="buyer_id")
	private String buyerId;
	@JSONField(name="buyer_logon_id")
	private String buyerLogonId;
	@JSONField(name="seller_id")
	private String sellerId;
	@JSONField(name="seller_email")
	private String sellerEmail;
	@JSONField(name="trade_status")
	private String tradeStatus;
	@JSONField(name="total_amount")
	private Integer totalAmount;
	@JSONField(name="receipt_amount")
	private Integer receiptAmount;
	@JSONField(name="invoice_amount")
	private Integer invoiceAmount;
	@JSONField(name="buyer_pay_amount")
	private Integer buyerPayAmount;
	@JSONField(name="poInteger_amount")
	private Integer poIntegerAmount;
	@JSONField(name="refund_fee")
	private Integer refundFee;
	private String subject;
	private String body;
	@JSONField(name="gmt_create")
	private String gmtCreate;
	@JSONField(name="gmt_payment")
	private String gmtPayment;
	@JSONField(name="gmt_refund")
	private String gmtRefund;
	@JSONField(name="gmt_close")
	private String gmtClose;
	@JSONField(name="fund_bill_list")
	private String fundBillList;
	@JSONField(name="passback_params")
	private String passbackParams;
	@JSONField(name="voucher_detail_list")
	private String voucherDetailList;
	public String getNotifyTime() {
		return notifyTime;
	}
	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}
	public String getNotifyType() {
		return notifyType;
	}
	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}
	public String getNotifyId() {
		return notifyId;
	}
	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getOutBizNo() {
		return outBizNo;
	}
	public void setOutBizNo(String outBizNo) {
		this.outBizNo = outBizNo;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getBuyerLogonId() {
		return buyerLogonId;
	}
	public void setBuyerLogonId(String buyerLogonId) {
		this.buyerLogonId = buyerLogonId;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getSellerEmail() {
		return sellerEmail;
	}
	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	public Integer getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = new BigDecimal(totalAmount).multiply(scale).intValue();
	}
	public Integer getReceiptAmount() {
		return receiptAmount;
	}
	public void setReceiptAmount(String receiptAmount) {
		this.receiptAmount = new BigDecimal(receiptAmount).multiply(scale).intValue();
	}
	public Integer getInvoiceAmount() {
		return invoiceAmount;
	}
	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = new BigDecimal(invoiceAmount).multiply(scale).intValue();
	}
	public Integer getBuyerPayAmount() {
		return buyerPayAmount;
	}
	public void setBuyerPayAmount(String buyerPayAmount) {
		this.buyerPayAmount = new BigDecimal(buyerPayAmount).multiply(scale).intValue();
	}
	public Integer getPoIntegerAmount() {
		return poIntegerAmount;
	}
	public void setPoIntegerAmount(String poIntegerAmount) {
		this.poIntegerAmount = new BigDecimal(poIntegerAmount).multiply(scale).intValue();
	}
	public Integer getRefundFee() {
		return refundFee;
	}
	public void setRefundFee(String refundFee) {
		this.refundFee = new BigDecimal(refundFee).multiply(scale).intValue();
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public String getGmtPayment() {
		return gmtPayment;
	}
	public void setGmtPayment(String gmtPayment) {
		this.gmtPayment = gmtPayment;
	}
	public String getGmtRefund() {
		return gmtRefund;
	}
	public void setGmtRefund(String gmtRefund) {
		this.gmtRefund = gmtRefund;
	}
	public String getGmtClose() {
		return gmtClose;
	}
	public void setGmtClose(String gmtClose) {
		this.gmtClose = gmtClose;
	}
	public String getFundBillList() {
		return fundBillList;
	}
	public void setFundBillList(String fundBillList) {
		this.fundBillList = fundBillList;
	}
	public String getPassbackParams() {
		return passbackParams;
	}
	public void setPassbackParams(String passbackParams) {
		this.passbackParams = passbackParams;
	}
	public String getVoucherDetailList() {
		return voucherDetailList;
	}
	public void setVoucherDetailList(String voucherDetailList) {
		this.voucherDetailList = voucherDetailList;
	}
	
}
