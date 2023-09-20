package com.wayakeji.payment.wxpay.pojo;

import com.wayakeji.payment.annotation.PayField;
import com.wayakeji.payment.common.CommonPojo;

/**
 * <p>微信退款参数类, 通过new此类可以快速构架参数
 * <p>此处提供四个构造方法, 请根据实际情况使用对应构造方法
 * <p>字段说明请见: <a href="https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_4&index=6">微信官方文档</a>
 * <pre>
 * 说明:
 *   两个参数构造, 针对使用transactionId(微信生成的订单号)查询时推荐使用, 需要手动调用{@link #setTransactionId(String)}
 *   三个参数构造, 推荐使用, 系统会自动使用{@link #nonceStr()}创建nonceStr和{@link #outRefundNo()}创建outRefundNo
 *   四个参数构造, 不推荐使用(当首次退款失败时二次退款推荐使用), 系统会自动使用{@link #nonceStr()}创建nonceStr, 需手动创建outRefundNo传入
 *   五个参数构造, 不推荐使用, 系统不会创建任何参数, 需要手动传入outRefundNo和nonceStr
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see CommonPojo
 * @since 1.8
 * @version 1.0
 * @time 2019年7月23日
 */
public class Refund extends CommonPojo {
	
	@PayField("nonce_str")
	protected  String nonceStr;
	@PayField("transaction_id")
	protected  String transactionId;
	@PayField("out_trade_no")
	protected  String outTradeNo;
	@PayField("out_refund_no")
	protected  String outRefundNo;
	@PayField("total_fee")
	protected  int totalFee;
	@PayField("refund_fee")
	protected  int refundFee;

	@PayField("refund_fee_type")
	protected  String refundFeeType;
	@PayField("refund_desc")
	protected  String refundFesc;
	@PayField("refund_account")
	protected  String refundAccount;
	@PayField("notify_url")
	protected  String notifyUrl;
	
	protected  void needful(int totalFee, int refundFee) {
		this.totalFee = totalFee;
		this.refundFee = refundFee;
	}
	
	/**
	 * <p>构建参数类, 此构造方法是包含所有必填参数
	 * @param outTradeNo 系统订单号
	 * @param totalFee 支付金额
	 * @param refundFee 退款金额
	 */
	public Refund(String outTradeNo, int totalFee, int refundFee) {
		needful(totalFee, refundFee);
		this.outTradeNo = outTradeNo;
		this.nonceStr = nonceStr();
		this.outRefundNo = outRefundNo();
	}
	
	/**
	 * <p>构建参数类, 此构造方法是包含所有必填参数
	 * @param outRefundNo 退款单号
	 * @param outTradeNo 系统订单号
	 * @param totalFee 支付金额
	 * @param refundFee 退款金额
	 */
	public Refund(String outRefundNo, String outTradeNo, int totalFee, int refundFee) {
		needful(totalFee, refundFee);
		this.outTradeNo = outTradeNo;
		this.nonceStr = nonceStr();
		this.outRefundNo = outRefundNo;
	}
	
	/**
	 * <p>构建参数类, 此构造方法是包含所有必填参数
	 * @param outRefundNo 退款单号
	 * @param nonceStr 随机数
	 * @param outTradeNo 系统订单号
	 * @param totalFee 支付金额
	 * @param refundFee 退款金额
	 */
	public Refund(String outRefundNo, String nonceStr, String outTradeNo, int totalFee, int refundFee) {
		needful(totalFee, refundFee);
		this.outTradeNo = outTradeNo;
		this.nonceStr = nonceStr;
		this.outRefundNo = outRefundNo;
	}

	/**
	 * <p>构建参数类, 此构造方法需要手动设置transactionId
	 * @param totalFee 支付金额
	 * @param refundFee 退款金额
	 */
	public Refund(int totalFee, int refundFee) {
		needful(totalFee, refundFee);
		this.nonceStr = nonceStr();
		this.outRefundNo = outRefundNo();
	}

	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getRefundFeeType() {
		return refundFeeType;
	}
	public void setRefundFeeType(String refundFeeType) {
		this.refundFeeType = refundFeeType;
	}
	public String getRefundFesc() {
		return refundFesc;
	}
	public void setRefundFesc(String refundFesc) {
		this.refundFesc = refundFesc;
	}
	public String getRefundAccount() {
		return refundAccount;
	}
	public void setRefundAccount(String refundAccount) {
		this.refundAccount = refundAccount;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public String getOutRefundNo() {
		return outRefundNo;
	}
	public int getTotalFee() {
		return totalFee;
	}
	public int getRefundFee() {
		return refundFee;
	}
	
}
