package jp.co.info.ais.asm.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Asset {
	int assetSeq;
	String assetNumber;
	String kubunCode;
	String purchaseDate;

	String startPurchaseDate;
	String endPurchaseDate;

	int warrantyPeriod;
	String buyTo;
	int purchasePrice;
	String storageLocation;
	String statusCode;
	String makerName;
	String modelName;
	String serialNum;
	String interFaceCol;
	String osName;
	String osProductKey;
	String cpuProcessor;
	double operatingFrequency;
	double memory;
	double memoryUpgarde;
	double hdd;
	double hddUpgarde;
	String macLan;
	String macWireless;
	String other;
	String securityBios;
	String securityHdd;
	String adminAccountId;
	String adminAccountPwd;

	String insertId;
	Date insertDate;
	String updateId;
	Date updateDate;

	String typeCode;

	int length = 10;
	int start;
}
