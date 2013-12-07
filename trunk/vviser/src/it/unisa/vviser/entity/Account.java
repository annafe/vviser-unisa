package it.unisa.vviser.entity;
/**
DOCUMENTAZIONE:
- COSA RAPPRESENTA LA CLASSE
- A COSA SERVE
- COME SI USA
@author Eugenio Gigante
@version 1.0
*/

public class Account {
private String nome;
private String cognome;
private String dataDiNascita;
private String comuneDiNascita;
private String provinciaDiNascita;
private String userName;
private String codiceFiscale;
private String password;
private String email;
private String dipartimento_Nome;
private String tipologia;

/**
DOCUMENTAZIONE:
- COSA FA IL METODO
- COME USARLO
@param x COSA RAPPRESENTA x?
@return COSA RESTITUISCE
@throws Ex QUANDO VIENE LANCIATA?
*/
public void setNome (String nome){
	this.nome=nome;
}
public String getNome(){
	return this.nome;
}

public void setCognome (String cognome){
	this.cognome=cognome;
}
public String getCognome(){
	return this.cognome;
}

public void setDataDiNascita (String dataDiNascita){
	this.dataDiNascita=dataDiNascita;
}
public String getDataDiNascita(){
	return this.dataDiNascita;
}

public void setComuneDiNascita (String comuneDiNascita){
	this.comuneDiNascita=comuneDiNascita;
}
public String getComuneDiNascita(){
	return this.comuneDiNascita;
}

public void setProvinciaDiNascita (String provinciaDiNascita){
	this.provinciaDiNascita=provinciaDiNascita;
}
public String getProvinciaDiNascita(){
	return this.provinciaDiNascita;
}

public void setUserName (String userName){
	this.userName=userName;
}
public String getUserName(){
	return this.userName;
}

public void setCodiceFiscale (String codiceFiscale){
	this.codiceFiscale=codiceFiscale;
}
public String getCodiceFiscale(){
	return this.codiceFiscale;
}

public void setPassword (String password){
	this.password=password;
}
public String getPassword(){
	return this.password;
}


public void setEmail (String email){
	this.email=email;
}
public String getemail(){
	return this.email;
}
public void setDipartimento_Nome (String dipartimento_Nome ){
	this.dipartimento_Nome =dipartimento_Nome ;
}
public String getDipartimento_Nome (){
	return this.dipartimento_Nome ;
}
public void setTipologia (String dipartimento_Nome ){
	this.tipologia  =tipologia  ;
}
public String getTipologia  (){
	return this.tipologia  ;
}
}
