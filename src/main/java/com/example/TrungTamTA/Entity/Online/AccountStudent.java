package com.example.TrungTamTA.Entity.Online;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name="account_student")
@Data
public class AccountStudent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Lob
	private String email;

	@Column(name="money_available")
	private float moneyAvailable;

	@Lob
	private String name;

	@Lob
	private String note;

	@Lob
	private String password;

	@Lob
	private String phone;

	@Column(name="updated_at")
	private Timestamp updatedAt;

}