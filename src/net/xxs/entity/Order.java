package net.xxs.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import net.xxs.util.JsonUtil;
import net.xxs.util.SerialNumberUtil;
import net.xxs.util.SettingUtil;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.ForeignKey;

/**
 * 实体类 - 订单
 */

@Entity
public class Order extends BaseEntity {

	private static final long serialVersionUID = -8541323033439515148L;

	// 订单状态（未处理、已处理、已完成、已作废）
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

	，名称，，厚度，工艺，凸码，签名条，磁条，条码，烫版，总货款，预付款，货款，接单，设计，公司名称，交货时间，交货方式，发货单号，拼版版号，加急单，合包，备注，
	
	
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
	private String sheji;// 收货地址
	private String sheji;// 收货地址
	private String sheji;// 收货地址
	private String shipZipCode;// 收货邮编
	private String shipPhone;// 收货电话
	private String shipMobile;// 收货手机
	private String memo;// 附言
	private String goodsIdListStore;// 商品ID集合储存
	
	private Member member;// 会员
	private DeliveryType deliveryType;// 配送方式
	private PaymentConfig paymentConfig;// 支付方式
	
	private String payStatus;//记录支付过程中的状态码
	
	private Set<OrderItem> orderItemSet = new HashSet<OrderItem>();// 订单项
	private Set<OrderLog> orderLogSet = new HashSet<OrderLog>();// 订单日志
	private Set<Payment> paymentSet = new HashSet<Payment>();// 收款
	private Set<Refund> refundSet = new HashSet<Refund>();// 退款
	private Set<Shipping> shippingSet = new HashSet<Shipping>();// 发货
	private Set<Reship> reshipSet = new HashSet<Reship>();// 退货
	
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

	@Enumerated
	@Column(nullable = false)
	public ShippingStatus getShippingStatus() {
		return shippingStatus;
	}

	public void setShippingStatus(ShippingStatus shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	@Column(nullable = false, precision = 15, scale = 5)
	public BigDecimal getTotalProductPrice() {
		return totalProductPrice;
	}
	
	public void setTotalProductPrice(BigDecimal totalProductPrice) {
		this.totalProductPrice = SettingUtil.setPriceScale(totalProductPrice);
	}
	
	@Column(nullable = false, precision = 15, scale = 5)
	public BigDecimal getDeliveryFee() {
		return deliveryFee;
	}
	
	public void setDeliveryFee(BigDecimal deliveryFee) {
		this.deliveryFee = SettingUtil.setPriceScale(deliveryFee);
	}
	
	@Column(nullable = false, precision = 15, scale = 5)
	public BigDecimal getPaymentFee() {
		return paymentFee;
	}

	public void setPaymentFee(BigDecimal paymentFee) {
		this.paymentFee = SettingUtil.setPriceScale(paymentFee);
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
	
	@Column(nullable = false)
	public String getDeliveryTypeName() {
		return deliveryTypeName;
	}
	
	public void setDeliveryTypeName(String deliveryTypeName) {
		this.deliveryTypeName = deliveryTypeName;
	}
	
	@Column(nullable = false)
	public String getPaymentConfigName() {
		return paymentConfigName;
	}

	public void setPaymentConfigName(String paymentConfigName) {
		this.paymentConfigName = paymentConfigName;
	}
	
	@Column(nullable = false)
	public Integer getTotalProductWeight() {
		return totalProductWeight;
	}

	public void setTotalProductWeight(Integer totalProductWeight) {
		this.totalProductWeight = totalProductWeight;
	}
	
	@Column(nullable = false)
	public Integer getTotalProductQuantity() {
		return totalProductQuantity;
	}

	public void setTotalProductQuantity(Integer totalProductQuantity) {
		this.totalProductQuantity = totalProductQuantity;
	}

	@Column(nullable = false)
	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	@Column(nullable = false, length = 3000)
	public String getShipAreaStore() {
		return shipAreaStore;
	}

	public void setShipAreaStore(String shipAreaStore) {
		this.shipAreaStore = shipAreaStore;
	}

	@Column(nullable = false)
	public String getShipAddress() {
		return shipAddress;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}
	@Column(nullable = false)
	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	@Column(nullable = false)
	public String getShipZipCode() {
		return shipZipCode;
	}

	public void setShipZipCode(String shipZipCode) {
		this.shipZipCode = shipZipCode;
	}
	@Column(length = 100)
	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getShipPhone() {
		return shipPhone;
	}

	public void setShipPhone(String shipPhone) {
		this.shipPhone = shipPhone;
	}

	public String getShipMobile() {
		return shipMobile;
	}

	public void setShipMobile(String shipMobile) {
		this.shipMobile = shipMobile;
	}
	
	@Column(length = 3000)
	public String getMemo() {
		return memo;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Column(length = 3000)
	public String getGoodsIdListStore() {
		return goodsIdListStore;
	}

	public void setGoodsIdListStore(String goodsIdListStore) {
		this.goodsIdListStore = goodsIdListStore;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name = "fk_order_member")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@ForeignKey(name = "fk_order_delivery_type")
	public DeliveryType getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(DeliveryType deliveryType) {
		this.deliveryType = deliveryType;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@ForeignKey(name = "fk_order_payment_config")
	public PaymentConfig getPaymentConfig() {
		return paymentConfig;
	}

	public void setPaymentConfig(PaymentConfig paymentConfig) {
		this.paymentConfig = paymentConfig;
	}

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
	@OrderBy("createDate asc")
	public Set<OrderItem> getOrderItemSet() {
		return orderItemSet;
	}

	public void setOrderItemSet(Set<OrderItem> orderItemSet) {
		this.orderItemSet = orderItemSet;
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

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
	@OrderBy("createDate desc")
	public Set<Refund> getRefundSet() {
		return refundSet;
	}

	public void setRefundSet(Set<Refund> refundSet) {
		this.refundSet = refundSet;
	}

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
	@OrderBy("createDate desc")
	public Set<Shipping> getShippingSet() {
		return shippingSet;
	}

	public void setShippingSet(Set<Shipping> shippingSet) {
		this.shippingSet = shippingSet;
	}

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
	@OrderBy("createDate desc")
	public Set<Reship> getReshipSet() {
		return reshipSet;
	}

	public void setReshipSet(Set<Reship> reshipSet) {
		this.reshipSet = reshipSet;
	}
	
	// 获取收货地区
	@Transient
	public Area getShipArea() {
		if (StringUtils.isEmpty(shipAreaStore)) {
			return null;
		}
		return JsonUtil.toObject(shipAreaStore, Area.class);
	}
	
	// 设置收货地区
	@Transient
	public void setShipArea(Area shipArea) {
		if (shipArea == null) {
			shipAreaStore = null;
			return;
		}
		shipAreaStore = JsonUtil.toJson(shipArea);
	}
	
	// 获取商品ID集合
	@SuppressWarnings("unchecked")
	@Transient
	public List<String> getGoodsIdList() {
		if (StringUtils.isEmpty(goodsIdListStore)) {
			return null;
		}
		return JsonUtil.toObject(goodsIdListStore, ArrayList.class);
	}
	
	// 设置商品ID集合
	@Transient
	public void setGoodsIdList(List<String> goodsIdList) {
		if (goodsIdList == null || goodsIdList.size() == 0) {
			goodsIdListStore = null;
			return;
		}
		goodsIdListStore = JsonUtil.toJson(goodsIdList);
	}
	
	// 保存处理
	@Override
	@Transient
	public void onSave() {
		orderSn = SerialNumberUtil.buildOrderSn();
	}

}