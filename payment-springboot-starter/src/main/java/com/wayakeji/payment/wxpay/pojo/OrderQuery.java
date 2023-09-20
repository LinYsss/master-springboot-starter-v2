package com.wayakeji.payment.wxpay.pojo;

import com.wayakeji.payment.annotation.PayField;
import com.wayakeji.payment.common.CommonPojo;

/**
 * <p>查询订单参数类, 通过new此类可以快速构架参数
 * <p>此处提供三个构造方法, 建议使用时在同一项目中统一某个构造方法
 * <p>字段说明请见: <a href="https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_2&index=4">微信官方文档</a>
 * <pre>
 * 说明:
 *   无参构造, 针对使用transactionId(微信生成的订单号)查询时推荐使用, 需要手动调用{@link #setTransactionId(String)}
 *   一个参数构造, 推荐使用, 使用outTradeNo(系统生成的单号)查询, 系统会自动使用{@link #nonceStr()}创建
 *   两个参数构造, 不推荐使用, outTradeNo(系统生成的单号), nonceStr(随机数)
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see CommonPojo
 * @since 1.8
 * @version 1.0
 * @time 2019年7月23日
 */
public class OrderQuery extends CommonPojo {
	
	@PayField("transaction_id")
	protected  String transactionId;
	@PayField("out_trade_no")
	protected  String outTradeNo;
	@PayField("nonce_str")
	protected  String nonceStr;
	
	public OrderQuery() {}
	
	/**
	 * <p>初始化参数实体, 传入必填字段
	 * @param outTradeNo 系统订单号
	 */
	public OrderQuery(String outTradeNo) {
		this.outTradeNo = outTradeNo;
		this.nonceStr = super.nonceStr();
	}
	
	/**
	 * <p>初始化参数实体, 传入必填字段
	 * @param outTradeNo 系统订单号
	 * @param nonceStr 随机字符串
	 */
	public OrderQuery(String outTradeNo, String nonceStr) {
		this.outTradeNo = outTradeNo;
		this.nonceStr = nonceStr;
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
	public String getNonceStr() {
		return nonceStr;
	}
	
}
