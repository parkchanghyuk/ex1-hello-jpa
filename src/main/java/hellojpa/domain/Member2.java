package hellojpa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(
	name = "MEMBER_SEQ_GENERATOR",
	sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
	initialValue = 1, allocationSize = 50)
public class Member2 {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "USERNAME")
	private String name;

	public Member2() {
	}

	public Member2(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
