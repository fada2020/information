package jp.co.info.ais.asm.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class ITAsset {
	String assetNumber;
	int psNum = -1;
	Date purchaseDate;
	Date startPurchaseDate;
	Date endPurchaseDate;
	int warrantyPeriod;
	String buyTo;
	int purchasePrice;
	String storageLocation;
	int sNumber = -1;
	String maker;
	String model;
	String serialNum;
	String interFaceCol;
	String OSName;
	String OSProductKey;
	String CPUProcessor;
	int operatingFrequency;
	int memory;
	int memoryUpgarde;
	int HDD;
	int HDDUpgarde;
	String MACLAN;
	String MACWireless;
	String other;
	String securityBIOS;
	String securityHDD;
	String adminAccountID;
	String adminAccountPW;

	int length = 10;
	int start;
}
