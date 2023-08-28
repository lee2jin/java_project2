package co.yedam.cashbook.income.service;

import java.util.Date;

import lombok.Data;

@Data
public class IncomeVO {
	private String inId;
	private int moneyIn;
	private String inGroup;
	private String inMemo;
	private Date inDate;
	
	public String toString() {
		System.out.print(inGroup + " | ");
		System.out.println("소득: " + moneyIn + "원");
		System.out.print("분류: " + inGroup);
		System.out.print(" | 입금일: " + inDate);
		System.out.println("메모: " + inMemo);
		return null;
	}
		
}
