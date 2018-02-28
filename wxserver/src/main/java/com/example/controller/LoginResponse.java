package com.example.controller;

import java.io.Serializable;

/**
 * @(#) 用户登陆返回对象
 *
 * @description : 
 * @author : 
 * @version: Version No.1
 * @copyright : FiberHome FHZ Telecommunication Technologies Co.Ltd.
 */
public class LoginResponse implements Serializable {
	
	private static final long serialVersionUID = 1234568L;
	
	private Integer errCode;
	private String sessionId;
	private String userName;
	private String userId;
	private String userPassword;
	private String cmsIp;
	private Integer cmsSipPort;
	private String firstScreenUrl;// 业务管理子系统首页面地址（用户权限允许访问的第一个页面）
	private String vsiIpAddr;// 
	private Integer vsiPort; //sip port
	
	// add by version manage use
	private Integer versionErrorCode;
	private String version;
	private String updateFlag;
	private String fileName;
	private Integer fileSize;
	private String downLoadUrl;
	private String versionDescription;
	private String dictTime;
	private String dictDownloadUrl;
	private Integer dictFileSize;
	private String theme;//空为默认黑色，2为蓝色
	//version manage use end
	
	private String reserve1;// 保留字段
	private Integer reserve2;
	
	// 增加双网双平台配置信息
	private String labVSIip;        //  视频资料库  服务地址
	private Integer labVSIport;      //  视频资料库  服务端口
	private String hasLabVSI; 	     //  是否有 视频资料库 服务
	private String hasVSI;          //  是否有 结构化子系统 服务
	private String shipinGonganFlag;   // 1:视频网  2：公安网 
	

	public String getLabVSIip() {
		return labVSIip;
	}

	public void setLabVSIip(String labVSIip) {
		this.labVSIip = labVSIip;
	}

	public Integer getLabVSIport() {
		return labVSIport;
	}

	public void setLabVSIport(Integer labVSIport) {
		this.labVSIport = labVSIport;
	}

	public String getHasLabVSI() {
		return hasLabVSI;
	}

	public void setHasLabVSI(String hasLabVSI) {
		this.hasLabVSI = hasLabVSI;
	}

	public String getHasVSI() {
		return hasVSI;
	}

	public void setHasVSI(String hasVSI) {
		this.hasVSI = hasVSI;
	}

	public LoginResponse() {
		super();
	}
	
	public LoginResponse(Integer errCode) {
		super();
		this.errCode = errCode;
	}

	public Integer getErrCode() {
		return errCode;
	}

	public void setErrCode(Integer errCode) {
		this.errCode = errCode;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getCmsIp() {
		return cmsIp;
	}

	public void setCmsIp(String cmsIp) {
		this.cmsIp = cmsIp;
	}

	public Integer getCmsSipPort() {
		return cmsSipPort;
	}

	public void setCmsSipPort(Integer cmsSipPort) {
		this.cmsSipPort = cmsSipPort;
	}

	public String getFirstScreenUrl() {
		return firstScreenUrl;
	}

	public void setFirstScreenUrl(String firstScreenUrl) {
		this.firstScreenUrl = firstScreenUrl;
	}

	public String getVsiIpAddr() {
		return vsiIpAddr;
	}

	public void setVsiIpAddr(String vsiIpAddr) {
		this.vsiIpAddr = vsiIpAddr;
	}

	public Integer getVsiPort() {
		return vsiPort;
	}

	public void setVsiPort(Integer vsiPort) {
		this.vsiPort = vsiPort;
	}

	public Integer getVersionErrorCode() {
		return versionErrorCode;
	}

	public void setVersionErrorCode(Integer versionErrorCode) {
		this.versionErrorCode = versionErrorCode;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	
	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}


	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public String getDownLoadUrl() {
		return downLoadUrl;
	}

	public void setDownLoadUrl(String downLoadUrl) {
		this.downLoadUrl = downLoadUrl;
	}

	public String getVersionDescription() {
		return versionDescription;
	}

	public void setVersionDescription(String versionDescription) {
		this.versionDescription = versionDescription;
	}

	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public Integer getReserve2() {
		return reserve2;
	}

	public void setReserve2(Integer reserve2) {
		this.reserve2 = reserve2;
	}

	public String getDictTime() {
		return dictTime;
	}

	public void setDictTime(String dictTime) {
		this.dictTime = dictTime;
	}

	public String getDictDownloadUrl() {
		return dictDownloadUrl;
	}

	public void setDictDownloadUrl(String dictDownloadUrl) {
		this.dictDownloadUrl = dictDownloadUrl;
	}

	public Integer getDictFileSize() {
		return dictFileSize;
	}

	public void setDictFileSize(Integer dictFileSize) {
		this.dictFileSize = dictFileSize;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getShipinGonganFlag() {
		return shipinGonganFlag;
	}

	public void setShipinGonganFlag(String shipinGonganFlag) {
		this.shipinGonganFlag = shipinGonganFlag;
	}
	
}
