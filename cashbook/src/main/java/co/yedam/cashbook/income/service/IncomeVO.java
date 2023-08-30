package co.yedam.cashbook.income.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class IncomeVO {
	private int moneyIn;
	private String inGroup;
	private String inMemo;
	private Date inDate;
	private int inId;
	private String inName;
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");	
		System.out.println("+--------------------------------------------------+");
		System.out.println("  [ 금액 ]" +moneyIn + "원\t" + "\t[ 분류 ]\t" + inGroup);
		System.out.println("  [ 입금일 ] " +sdf.format(inDate)+"\t[ 메모 ]\t" + inMemo);
		return null;
	}
	
	public String simpleString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");	
		System.out.println("+--------------------------------------------------+");
		System.out.println("  [ 금액 ] " + moneyIn + "원" + "\t[ 입금일 ] " + sdf.format(inDate));
		return null;
	}
		
}
