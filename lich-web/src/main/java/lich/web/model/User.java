package lich.web.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * 用户实体
 * @author Lich
 * 2014年7月21日 上午9:55:35
 */
@Entity
@Table(name="user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * AUTO,INDENTITY,SEQUENCE 和 TABLE
	 * 
	 * insertable=false,updatable=false
	 * 字段值为只读的，不允许插入和修改。通常用于主键和外键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", insertable=false, updatable=false, columnDefinition="int", scale=19)
	private Long id;
	/**
	 * length=20, unique=true, nullable=false
	 * 最大长度为20，不允许插入与更新，true为允许，
	 */
	@Column(name="account", length=100, unique=true, nullable=false)
	private String account;
	
	@Column(name="real_name")
	private String realName;
	
	@Column(name="password", length=50, nullable=false)
	private String password;
	
	@Column(name="sex", length=4)
	private String sex;
	
	/**
	 * optional = true设置idcard属性可以为null
	 * cascade = CascadeType.ALL 设置级联刷新与更新
	 */
	@OneToOne(optional=true, cascade=CascadeType.ALL)
	@JoinColumn(name="id_card", referencedColumnName="id", unique=true)
	private IdentifyCard idCard;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public IdentifyCard getIdCard() {
		return idCard;
	}

	public void setIdCard(IdentifyCard idCard) {
		this.idCard = idCard;
	}

}
