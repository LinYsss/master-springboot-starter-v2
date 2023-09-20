package com.wayakeji.payment.wxpay.pojo;

import com.wayakeji.payment.annotation.PayField;
import com.wayakeji.payment.common.CommonPojo;
import com.wayakeji.payment.common.WeChatTradeType;

/**
 * <p>APP统一下单参数类, 通过new此类可以快速构架参数
 * <p>此处提供3个构造方法, 使用时在同一项目中请统一使用某个构造方法
 * <p>字段说明请见: <a href="https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_1">微信官方文档</a>
 * <pre>
 * 说明:
 *   六个参数的构造方法, 其中outTradeNo(订单号)与nonceStr(随机数)需要调用方提供
 *   五个参数的构造方法, 其中outTradeNo(订单号)需要调用方提供, nonceStr(随机数)会自动创建
 *   四个参数的构造方法, 其中outTradeNo(订单号)与nonceStr(随机数)均系统自动创建, 可使用{@link #getOutTradeNo()}和{@link #getNonceStr()}获取
 *   推荐使用系统自动创建的nonceStr(随机数), 使用{@link #nonceStr()}创建
 *   outTradeNo根据商品单号要求进行取舍, 此处使用{@link #outTradeNo()}创建
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see CommonPojo
 * @since 1.8
 * @version 1.0
 * @time 2019年7月23日
 */
public class UnifiedOrder extends CommonPojo {

	@PayField("out_trade_no")
	protected String outTradeNo;
	@PayField("nonce_str")
	protected String nonceStr;
	@PayField("body")
	protected String body;//商品描述  必须
	@PayField("total_fee")
	protected Integer totalFee;
	@PayField("spbill_create_ip")
	protected String spbillCreateIp;
	@PayField("notify_url")
	protected String notifyUrl;
	@PayField("trade_type")
	protected String tradeType;
	@PayField("fee_type")
	protected String feeType;
	@PayField("device_info")
	protected String deviceInfo;
	protected String detail;
	@PayField("time_start")
	protected String timeStart;
	@PayField("time_expire")
	protected String timeExpire;
	@PayField("goods_tag")
	protected String goodsTag;
	@PayField("limit_pay")
	protected String limitPay;
	@PayField("sub_mch_id")
	protected String subMchId;//子商户号， 服务商模式下使用
	@PayField("receipt")
	protected String receipt;
	@PayField("openid")
	protected String openid;// trade_type=JSAPI时（即JSAPI支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识。
	@PayField("product_id")
	protected String productId;//trade_type=NATIVE时，此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
	//附加参数
	@PayField("attach")
	protected String attach;


	public UnifiedOrder(Integer totalFee, String body, String notifyUrl, WeChatTradeType tradeType) {
		this.totalFee = totalFee;
		this.body = body;
		this.outTradeNo = super.outTradeNo();
		nonceStr = super.nonceStr();
		this.spbillCreateIp = "127.0.0.1";
		this.tradeType = tradeType.name();
		this.notifyUrl = notifyUrl;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public String getBody() {
		return body;
	}

	public int getTotalFee() {
		return totalFee;
	}

	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public String getTradeType() {
		return tradeType;
	}

	public String getFeeType() {
		return feeType;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public String getDetail() {
		return detail;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public String getTimeExpire() {
		return timeExpire;
	}

	public String getGoodsTag() {
		return goodsTag;
	}

	public String getLimitPay() {
		return limitPay;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
	}

	public void setGoodsTag(String goodsTag) {
		this.goodsTag = goodsTag;
	}

	public void setLimitPay(String limitPay) {
		this.limitPay = limitPay;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getSubMchId() {
		return subMchId;
	}

	public void setSubMchId(String subMchId) {
		this.subMchId = subMchId;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
}
