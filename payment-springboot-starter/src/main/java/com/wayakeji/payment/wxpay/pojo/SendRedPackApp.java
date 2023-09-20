package com.wayakeji.payment.wxpay.pojo;

import com.wayakeji.payment.annotation.PayField;
import com.wayakeji.payment.common.CommonPojo;

/**
 * <p>发送红包的参数类
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see CommonPojo
 * @since 1.8
 * @version 1.0
 * @time 2019年9月10日
 */
public class SendRedPackApp extends CommonPojo {
	
	@PayField("nonce_str")
	protected  String nonceStr;
	@PayField("mch_billno")
	protected  String mchBillno;
	@PayField("send_name")
	protected  String sendName;
	@PayField("re_openid")
	protected  String reOpenid;
	@PayField("total_amount")
	protected  int totalAmount;
	@PayField("total_num")
	protected  int totalNum;
	@PayField("wishing")
	protected  String wishing;
	@PayField("client_ip")
	protected  String clientIp;
	@PayField("act_name")
	protected  String actName;
	@PayField("remark")
	protected  String remark;
	@PayField("scene_id")
	protected  String sceneId;
	@PayField("risk_info")
	protected  String riskInfo;
	@PayField("notify_way")
	protected  String notifyWay = "MINI_PROGRAM_JSAPI";
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getMchBillno() {
		return mchBillno;
	}
	public void setMchBillno(String mchBillno) {
		this.mchBillno = mchBillno;
	}
	public String getSendName() {
		return sendName;
	}
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	public String getReOpenid() {
		return reOpenid;
	}
	public void setReOpenid(String reOpenid) {
		this.reOpenid = reOpenid;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public String getWishing() {
		return wishing;
	}
	public void setWishing(String wishing) {
		this.wishing = wishing;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSceneId() {
		return sceneId;
	}
	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}
	public String getRiskInfo() {
		return riskInfo;
	}
	public void setRiskInfo(String riskInfo) {
		this.riskInfo = riskInfo;
	}
	
	/**
	 * <p>构造函数, 需要传入基本必要参数
	 * @param sendName 商户名称	
	 * @param reOpenid 用户openid
	 * @param totalAmount 付款金额，单位分
	 * @param totalNum 红包发放总人数
	 * @param wishing 红包祝福语
	 * @param actName 活动名称
	 * @param remark 备注
	 */
	public SendRedPackApp(String sendName, String reOpenid, int totalAmount, int totalNum, String wishing, String actName, String remark) {
		this(sendName, reOpenid, totalAmount, totalNum, wishing, null, actName, remark);
		clientIp = "127.0.0.1";
	}
	
	/**
	 * <p>构造函数, 传入基本必要参数、clientIp
	 * @param sendName 商户名称	
	 * @param reOpenid 用户openid
	 * @param totalAmount 付款金额，单位分
	 * @param totalNum 红包发放总人数
	 * @param wishing 红包祝福语
	 * @param clientIp Ip地址	 默认127.0.0.1
	 * @param actName 活动名称
	 * @param remark 备注
	 */
	public SendRedPackApp(String sendName, String reOpenid, int totalAmount, int totalNum, String wishing, String clientIp, String actName, String remark) {
		this(null, sendName, reOpenid, totalAmount, totalNum, wishing, clientIp, actName, remark);
		mchBillno = super.outTradeNo();
	}
	
	/**
	 * <p>构造函数, 传入基本必要参数、clientIp、mchBillno
	 * @param mchBillno 商户订单号
	 * @param sendName 商户名称
	 * @param reOpenid 用户openid
	 * @param totalAmount 付款金额，单位分
	 * @param totalNum 红包发放总人数
	 * @param wishing 红包祝福语
	 * @param clientIp Ip地址	 默认127.0.0.1
	 * @param actName 活动名称
	 * @param remark 备注
	 */
	public SendRedPackApp(String mchBillno, String sendName, String reOpenid, int totalAmount, int totalNum, String wishing, String clientIp, String actName, String remark) {
		this(null, mchBillno, sendName, reOpenid, totalAmount, totalNum, wishing, clientIp, actName, remark);
		nonceStr = super.nonceStr();
	}
	
	/**
	 * <p>构造函数, 传入基本必要参数、clientIp、mchBillno、nonceStr
	 * @param nonceStr 随机字符串
	 * @param mchBillno 商户订单号
	 * @param sendName 商户名称
	 * @param reOpenid 用户openid
	 * @param totalAmount 付款金额，单位分
	 * @param totalNum 红包发放总人数
	 * @param wishing 红包祝福语
	 * @param clientIp Ip地址	 默认127.0.0.1
	 * @param actName 活动名称
	 * @param remark 备注
	 */
	public SendRedPackApp(String nonceStr, String mchBillno, String sendName, String reOpenid,
			int totalAmount, int totalNum, String wishing, String clientIp, String actName, String remark) {
		this.nonceStr = nonceStr;
		this.mchBillno = mchBillno;
		this.sendName = sendName;
		this.reOpenid = reOpenid;
		this.totalAmount = totalAmount;
		this.totalNum = totalNum;
		this.wishing = wishing;
		this.clientIp = clientIp;
		this.actName = actName;
		this.remark = remark;
	}
	public String getNotifyWay() {
		return notifyWay;
	}
	public void setNotifyWay(String notifyWay) {
		this.notifyWay = notifyWay;
	}
	
}
