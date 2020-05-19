package com.example.stock.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String login;
    private String pwd;
    private boolean bloqued;
    private int nbrTentatifRestant;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateBloquage;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean isBloqued() {
        return bloqued;
    }

    public void setBloqued(boolean bloqued) {
        this.bloqued = bloqued;
    }

    public int getNbrTentatifRestant() {
        return nbrTentatifRestant;
    }

    public void setNbrTentatifRestant(int nbrTentatifRestant) {
        this.nbrTentatifRestant = nbrTentatifRestant;
    }

    public Date getDateBloquage() {
        return dateBloquage;
    }

    public void setDateBloquage(Date dateBloquage) {
        this.dateBloquage = dateBloquage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

	public User(Long id, String login, String pwd, boolean bloqued, int nbrTentatifRestant, Date dateBloquage) {
		super();
		this.id = id;
		this.login = login;
		this.pwd = pwd;
		this.bloqued = bloqued;
		this.nbrTentatifRestant = nbrTentatifRestant;
		this.dateBloquage = dateBloquage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", pwd=" + pwd + ", bloqued=" + bloqued + ", nbrTentatifRestant="
				+ nbrTentatifRestant + ", dateBloquage=" + dateBloquage + "]";
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


}
