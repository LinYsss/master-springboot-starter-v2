package com.wayakeji.payment.wxpay.pojo;

import com.wayakeji.payment.annotation.PayField;
import com.wayakeji.payment.common.CommonPojo;

/**
 * <p>关闭订单参数类, 通过new此类可以快速构架参数
 * <p><b>注: 订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟</b>
 * <p>此处提供两个构造方法, 建议使用时在同一项目中统一某个构造方法
 * <p>字段说明请见: <a href="https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_3&index=5">微信官方文档</a>
 * <pre>
 * 说明:
 *   一个参数构造, 推荐使用, 使用outTradeNo(系统生成的单号)查询, 系统会自动使用{@link #nonceStr()}创建
 *   两个参数构造, 不推荐使用, outTradeNo(系统生成的单号), nonceStr(随机数)
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see CommonPojo
 * @since 1.8
 * @version 1.0
 * @time 2019年7月23日
 */
public class CloseOrder extends CommonPojo {
	
	@PayField("out_trade_no")
	protected  String outTradeNo;
	@PayField("nonce_str")
	protected  String nonceStr;
	
	/**
	 * <p>初始化参数实体, 传入必填字段
	 * @param outTradeNo 系统订单号
	 */
	public CloseOrder(String outTradeNo) {
		this.outTradeNo = outTradeNo;
		this.nonceStr = super.nonceStr();
	}
	
	/**
	 * <p>初始化参数实体, 传入必填字段
	 * @param outTradeNo 系统订单号
	 * @param nonceStr 随机字符串
	 */
	public CloseOrder(String outTradeNo, String nonceStr) {
		this.outTradeNo = outTradeNo;
		this.nonceStr = nonceStr;
	}
	
}
