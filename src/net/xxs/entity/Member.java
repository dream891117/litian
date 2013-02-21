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
 * 实体类 - 会员
 */

@Entity
public class Member extends BaseEntity {

	private static final long serialVersionUID = 1533130686714725835L;
	
	private String username;// 用户名
	private String password;// 密码
	private String email;// E-mail
	private String safeQuestion;// 密码保护问题
	private String safeAnswer;// 密码保护问题答案
	private String passwordRecoverKey;// 密码找回Key
	private Integer score;// 积分
	private BigDecimal deposit;// 预存款
	private Boolean isAccountEnabled;// 账号是否启用
	private Boolean isAccountLocked;// 账号是否锁定
	private Integer loginFailureCount;// 连续登录失败的次数
	private Date lockedDate;// 账号锁定日期
	private String registerIp;// 注册IP
	private String loginIp;// 最后登录IP
	private Date loginDate;// 最后登录日期
	private String name;// 姓名
	private Gender gender;// 性别
	private Date birth;// 出生日期
	private String areaStore;// 地区存储
	private String address;// 地址
	private String zipCode;// 邮编
	private String phone;// 电话
	private String mobile;// 手机
	private String referrer;// 推荐人

	
	private Set<Receiver> receiverSet = new HashSet<Receiver>();// 收货地址
	private Set<Order> orderSet = new HashSet<Order>();// 订单
	private Set<Payment> paymentSet = new HashSet<Payment>();// 支付

	@Column(nullable = false, updatable = false, unique = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSafeQuestion() {
		return safeQuestion;
	}

	public void setSafeQuestion(String safeQuestion) {
		this.safeQuestion = safeQuestion;
	}

	public String getSafeAnswer() {
		return safeAnswer;
	}

	public void setSafeAnswer(String safeAnswer) {
		this.safeAnswer = safeAnswer;
	}
	
	public String getPasswordRecoverKey() {
		return passwordRecoverKey;
	}

	public void setPasswordRecoverKey(String passwordRecoverKey) {
		this.passwordRecoverKey = passwordRecoverKey;
	}
	
	@Column(nullable = false)
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Column(nullable = false, precision = 15, scale = 5)
	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = SettingUtil.setPriceScale(deposit);
	}
	
	@Column(nullable = false)
	public Boolean getIsAccountEnabled() {
		return isAccountEnabled;
	}

	public void setIsAccountEnabled(Boolean isAccountEnabled) {
		this.isAccountEnabled = isAccountEnabled;
	}

	@Column(nullable = false)
	public Boolean getIsAccountLocked() {
		return isAccountLocked;
	}

	public void setIsAccountLocked(Boolean isAccountLocked) {
		this.isAccountLocked = isAccountLocked;
	}

	@Column(nullable = false)
	public Integer getLoginFailureCount() {
		return loginFailureCount;
	}

	public void setLoginFailureCount(Integer loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}
	
	public Date getLockedDate() {
		return lockedDate;
	}

	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}
	
	@Column(nullable = false, updatable = false)
	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}
	
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	
	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getAreaStore() {
		return areaStore;
	}

	public void setAreaStore(String areaStore) {
		this.areaStore = areaStore;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
	@OrderBy("createDate asc")
	public Set<Receiver> getReceiverSet() {
		return receiverSet;
	}

	public void setReceiverSet(Set<Receiver> receiverSet) {
		this.receiverSet = receiverSet;
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

	// 获取地区
	@Transient
	public Area getArea() {
		if (StringUtils.isEmpty(areaStore)) {
			return null;
		}
		return JsonUtil.toObject(areaStore, Area.class);
	}
	
	// 设置地区
	@Transient
	public void setArea(Area area) {
		if (area == null) {
			areaStore = null;
			return;
		}
		areaStore = JsonUtil.toJson(area);
	}
	
	
	// 保存处理
	@Override
	@Transient
	public void onSave() {
		if (score == null || score < 0) {
			score = 0;
		}
		if (deposit == null || deposit.compareTo(new BigDecimal(0)) < 0) {
			deposit = new BigDecimal(0);
		}
		if (isAccountEnabled == null) {
			isAccountEnabled = false;
		}
		if (isAccountLocked == null) {
			isAccountLocked = false;
		}
		if (loginFailureCount == null || loginFailureCount < 0) {
			loginFailureCount = 0;
		}
	}
	
	// 更新处理
	@Override
	@Transient
	public void onUpdate() {
		if (score == null || score < 0) {
			score = 0;
		}
		if (deposit == null || deposit.compareTo(new BigDecimal(0)) < 0) {
			deposit = new BigDecimal(0);
		}
		if (isAccountEnabled == null) {
			isAccountEnabled = false;
		}
		if (isAccountLocked == null) {
			isAccountLocked = false;
		}
		if (loginFailureCount == null || loginFailureCount < 0) {
			loginFailureCount = 0;
		}
	}
	
}