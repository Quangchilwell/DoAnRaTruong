package com.example.TrungTamTA.Entity.Online;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.sql.Timestamp;


@Entity
@Table(name="detail_add_money")
@Data
public class DetailAddMoney implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="id_account")
	private int idAccount;

	private float money;
}