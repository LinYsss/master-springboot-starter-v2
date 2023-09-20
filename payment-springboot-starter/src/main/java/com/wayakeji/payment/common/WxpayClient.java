package com.wayakeji.payment.common;

import com.wayakeji.payment.wxpay.WxpayConfig;
import com.wayakeji.payment.wxpay.module.*;
import com.wayakeji.payment.wxpay.pojo.*;

/**
 * <p>微信支付客户端, 通过此类可以快速构建需要执行的操作
 * <p>快速构建{@link Module}, 通过{@link Module}中的类型构建参数
 *
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @version 1.0
 * @time 2019年7月23日
 * @see Module
 * @since 1.8
 */
public class WxpayClient {

	protected WxpayConfig config;

	/**
	 * <p>初始化构造函数
	 *
	 * @param config
	 */
	public WxpayClient(WxpayConfig config) {
		this.config = config;
	}

	/**
	 * <p>构建统一下单模块, 通过返回值直接进行调用模块
	 *
	 * @return {@link UnifiedOrder}类型的{@link Module}
	 */
	public Module<UnifiedOrder> createUnifiedOrderAppModule() {
		return new UnifiedOrderModule(config);
	}

	/**
	 * <p>构建订单查询模块, 通过返回值直接进行调用模块
	 *
	 * @return {@link UnifiedOrder}类型的{@link Module}
	 */
	public Module<OrderQuery> createOrderQueryAppModule() {
		return new OrderQueryModule(config);
	}

	/**
	 * <p>构建关闭订单模块, 通过返回值直接进行调用模块
	 *
	 * @return {@link UnifiedOrder}类型的{@link Module}
	 */
	public Module<CloseOrder> createCloseOrderAppModule() {
		return new CloseOrderModule(config);
	}

	/**
	 * <p>构建手机APP关闭订单模块, 通过返回值直接进行调用模块
	 *
	 * @return {@link UnifiedOrder}类型的{@link Module}
	 */
	public Module<Refund> createRefundModule() {
		return new RefundModule(config);
	}

	/**
	 * <p>构建微信发送红包模块, 通过返回module直接调用模块
	 *
	 * @return {@link SendRedPack}类型的{@link Module}
	 */
	public Module<SendRedPack> createSendRedPack() {
		return new SendRedPackModule(config);
	}

	/**
	 * <p>构建微信小程序发送红包模块, 通过返回module直接调用模块
	 *
	 * @return {@link SendRedPack}类型的{@link Module}
	 */
	public Module<SendRedPackApp> createSendRedPackApp() {
		return new SendRedPackAppModule(config);
	}

	/**
	 * <p>构建企业转账到个人用户模块, 通过返回module直接调用模块
	 *
	 * @return {@link EnterpriseTransfers}类型的{@link Module}
	 */
	public Module<EnterpriseTransfers> createEnterpriseTransfersModule() {
		return new EnterpriseTransfersModule(config);
	}

	/**
	 * <p>构建查询企业转账到个人用户模块, 通过返回module直接调用模块
	 *
	 * @return {@link EnterpriseTransfersQuery}类型的{@link Module}
	 */
	public Module<EnterpriseTransfersQuery> createEnterpriseTransfersQueryModule() {
		return new EnterpriseTransfersQueryModule(config);
	}

	public WxpayConfig getConfig() {
		return config;
	}

}
