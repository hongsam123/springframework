package com.mycompany.webapp.dto;

import lombok.Data;

//lombok으로 getter, setter를 만든다
@Data
public class Ch11Member {
	private String mid;
	private String mname;
	private String mpassword;
	private String mnation;
	private String mtype;
	private String mjob;
	private int mcity;
	private String[] mlanguage;
	private String[] mskill;
}
