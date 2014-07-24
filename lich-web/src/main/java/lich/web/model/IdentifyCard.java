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
 * 身份证实体
 * @author Lich
 * 2014年7月23日 下午4:37:12
 */
@Entity
@Table(name="identify_card")
public class IdentifyCard implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", insertable=false, updatable=false, columnDefinition="int", scale=19)
	private Long id;
	
	@Column(name="card_num", unique=true)
	private String cardNum;
	
	/**
	 * JoinColumn(name = "user_id", referencedColumnName ="id",unique = true)指明IdentifyCard对应表的user_id列作为外键与 
	 * User对应表的id列进行关联,unique= true 指明user_id 列的值不可重复
	 * mappedBy="idCard" 
	 */
	@OneToOne(cascade=CascadeType.ALL, mappedBy="idCard")
//	@JoinColumn(name="user_id", referencedColumnName="id", unique=true)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
