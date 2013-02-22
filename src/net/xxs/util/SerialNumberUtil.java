package net.xxs.util;

import net.xxs.service.OrderService;
import net.xxs.service.PaymentService;

import org.apache.commons.lang.StringUtils;

/**
 * 工具类 - 编号生成
 */

public class SerialNumberUtil {
	
	public static final String ORDER_SN_PREFIX = "DD";// 订单编号前缀
	public static final long ORDER_SN_FIRST = 100000L;// 订单编号起始数
	public static final long ORDER_SN_STEP = 1L;// 订单编号步长
	
	public static final String PAYMENT_SN_PREFIX = "ZF";// 支付编号前缀
	public static final long PAYMENT_SN_FIRST = 100000L;// 支付编号起始数
	public static final long PAYMENT_SN_STEP = 1L;// 支付编号步长
	
	public static Long lastOrderSnNumber;
	public static Long lastPaymentSnNumber;

	static {
		// 订单编号
		OrderService orderService = (OrderService) SpringUtil.getBean("orderServiceImpl");
		String lastOrderSn = orderService.getLastOrderSn();
		if (StringUtils.isNotEmpty(lastOrderSn)) {
			lastOrderSnNumber = Long.parseLong(StringUtils.removeStartIgnoreCase(lastOrderSn, ORDER_SN_PREFIX));
		} else {
			lastOrderSnNumber = ORDER_SN_FIRST;
		}
		
		// 支付编号
		PaymentService paymentService = (PaymentService) SpringUtil.getBean("paymentServiceImpl");
		String lastPaymentSn = paymentService.getLastPaymentSn();
		if (StringUtils.isNotEmpty(lastPaymentSn)) {
			lastPaymentSnNumber = Long.parseLong(StringUtils.removeStartIgnoreCase(lastPaymentSn, PAYMENT_SN_PREFIX));
		} else {
			lastPaymentSnNumber = PAYMENT_SN_FIRST;
		}
		
	}	
	/**
	 * 生成订单编号
	 * 
	 * @return 订单编号
	 */
	public synchronized static String buildOrderSn() {
		
		lastOrderSnNumber += ORDER_SN_STEP;
		return ORDER_SN_PREFIX + lastOrderSnNumber;
	}
	
	/**
	 * 生成支付编号
	 * 
	 * @return 支付编号
	 */
	public synchronized static String buildPaymentSn() {
		lastPaymentSnNumber += PAYMENT_SN_STEP;
		return PAYMENT_SN_PREFIX + lastPaymentSnNumber;
	}

}