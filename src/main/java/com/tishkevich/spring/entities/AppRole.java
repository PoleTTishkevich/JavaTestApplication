package com.tishkevich.spring.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "App_Role", //
		uniqueConstraints = { //
				@UniqueConstraint(name = "APP_ROLE_UK", columnNames = "Role_Name") })
class AppRole {

	@Id
	@GeneratedValue
	@Column(name = "Role_Id", nullable = false)
	private Long roleId;

	@Column(name = "Role_Name", length = 30, nullable = false)
	private String roleName;
}
