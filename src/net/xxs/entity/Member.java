package net.xxs.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import net.xxs.bean.Gender;
import net.xxs.util.JsonUtil;
import net.xxs.util.SettingUtil;

import org.apache.commons.lang.StringUtils;

/**
 * 实体类 - 客户档案表
 */

@Entity
public class Member extends BaseEntity {

	private static final long serialVersionUID = 1533130686714725835L;
	
	
	private String corpName;// 公司名称
	private String linkMan;// 联系人
	private String linkTel;// 固话
	private String linkPhone;// 手机
	private String sendAddress;// 发货地址
	private String addressDetail;// 详细地址
	private String sendType;// 交货方式
	private String creditRank;// 信用等级
	
	private Set<Order> orderSet = new HashSet<Order>();// 订单
	private Set<Payment> paymentSet = new HashSet<Payment>();// 支付

	@Column(nullable = false, updatable = false, unique = true)
	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getLinkTel() {
		return linkTel;
	}

	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getSendAddress() {
		return sendAddress;
	}

	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getCreditRank() {
		return creditRank;
	}

	public void setCreditRank(String creditRank) {
		this.creditRank = creditRank;
	}

	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	@OrderBy("createDate desc")
	public Set<Order> getOrderSet() {
		return orderSet;
	}
	
	public void setOrderSet(Set<Order> orderSet) {
		this.orderSet = orderSet;
	}

	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	@OrderBy("createDate asc")
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
	}
	
	// 更新处理
	@Override
	@Transient
	public void onUpdate() {
	}
	
}