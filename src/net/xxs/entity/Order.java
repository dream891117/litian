package net.xxs.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import net.xxs.util.SerialNumberUtil;
import net.xxs.util.SettingUtil;

import org.hibernate.annotations.ForeignKey;

/**
 * 实体类 - 业务单
 */

@Entity
public class Order extends BaseEntity {

	private static final long serialVersionUID = -8541323033439515148L;

	// 订单状态（未拼版、已拼版、已完成、已作废）
	public enum OrderStatus {
		unprocessed, processed, completed, invalid
	};

	// 付款状态（未支付、部分支付、已支付、部分退款、全额退款）
	public enum PaymentStatus {
		unpaid, partPayment, paid, partRefund, refunded
	};

	// 配送状态（未发货、部分发货、已发货、部分退货、已退货）
	public enum ShippingStatus {
		unshipped, partShipped, shipped, partReshiped, reshiped
	};

	private String orderSn;// 工单号
	private String orderName;// 名称
	private OrderStatus orderStatus;// 订单状态
	private PaymentStatus paymentStatus;// 支付状态
	private Integer cardSl;// 数量
	private String cardHd;// 厚度
	private String cardGy;// 工艺
	private String cardTu;// 凸码
	private String cardQm;// 签名条
	private String cardCt;// 磁条
	private String cardTm;// 条码
	private String cardTb;// 烫板
	private BigDecimal totalAmount;// 总货款
	private BigDecimal paidAmount;// 预付款
	private BigDecimal Amount;// 货款
	private String jiedan;// 接单
	private String sheji;// 设计
	private Date jhDate;// 交货时间
	private String jhType;// 交货方式
	private String shipSn;// 发货单号
	private String pinbanSn;// 拼版版号
	private Boolean isJiaji;// 加急单
	private String memo;// 合包备注
	
	private Member member;// 会员
	
	private Set<OrderLog> orderLogSet = new HashSet<OrderLog>();// 订单日志
	private Set<Payment> paymentSet = new HashSet<Payment>();// 收款
	
	@Column(nullable = false, updatable = false, unique = true)
	public String getOrderSn() {
		return orderSn;
	}
	
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	
	@Enumerated
	@Column(nullable = false)
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Enumerated
	@Column(nullable = false)
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Column(nullable = false, precision = 15, scale = 5)
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = SettingUtil.setPriceScale(totalAmount);
	}

	@Column(nullable = false, precision = 15, scale = 5)
	public BigDecimal getPaidAmount() {
		return paidAmount;
	}
	
	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = SettingUtil.setPriceScale(paidAmount);
	}
	
	@Column(length = 3000)
	public String getMemo() {
		return memo;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}
	

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name = "fk_order_member")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
	@OrderBy("createDate asc")
	public Set<OrderLog> getOrderLogSet() {
		return orderLogSet;
	}

	public void setOrderLogSet(Set<OrderLog> orderLogSet) {
		this.orderLogSet = orderLogSet;
	}

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
	@OrderBy("createDate desc")
	public Set<Payment> getPaymentSet() {
		return paymentSet;
	}

	public void setPaymentSet(Set<Payment> paymentSet) {
		this.paymentSet = paymentSet;
	}

	// 保存处理
	@Override
	@Transient
	public void onSave() {
		orderSn = SerialNumberUtil.buildOrderSn();
	}

}