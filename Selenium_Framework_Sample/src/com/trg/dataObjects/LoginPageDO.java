package com.trg.dataObjects;

public class LoginPageDO {

	String testCaseDesc = null;
	String userName = null;
	String passWord = null;
	
	public String getTestCaseDesc() {
		return testCaseDesc;
	}
	public void setTestCaseDesc(String testCaseDesc) {
		this.testCaseDesc = testCaseDesc;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	@Override
    public String toString() {
        return "TestLoginData{" +
                "testCaseDesc='" + testCaseDesc + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\''+
                '}';
    }
}
