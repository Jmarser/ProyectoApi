package com.jmarser.api.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "login")
public class Login implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="email", unique = true)
	private String email;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name="rol", nullable = false)
	private String rol;
	
	@Column(name="activo", nullable = false)
	private boolean activo;
	
	public Login() {
	}
	
	public Login(String email, String password, String rol, boolean activo) {
		this.email = email;
		this.password = password;
		this.rol = rol;
		this.activo = activo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}
