package hellojpa;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(
	name = "MEMBER_SEQ_GENERATOR",
	sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
	initialValue = 1, allocationSize = 50)
public class Member2 {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
		generator = "MEMBER_SEQ_GENERATOR")
	private Long id;
	@Column(name = "name")
	private String username;

	public Member2() {
	}

	public Member2(Long id, String name) {
		this.id = id;
		this.username = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
