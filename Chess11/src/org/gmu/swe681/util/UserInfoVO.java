/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gmu.swe681.util;

/**
 *
 * @author nafiseh
 */
public class UserInfoVO {
        String userName;
	String passWord;
        String fname;
        String lname;
        String email;

	public String getuserName() {
		return userName;
	}
	public void setuserName(String userName) {
		this.userName = userName;
	}
        public String getpassWord() {
		return passWord;
	}
	public void setpassWord(String passWord) {
		this.passWord= passWord;
	}
        public String getfirstName() {
		return fname;
	}
	public void setfirstName(String fname) {
		this.fname= fname;
	}
        public String getlname() {
		return lname;
	}
	public void setlname(String lname) {
		this.lname =lname;
	}
        public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email =email;
	}

}
