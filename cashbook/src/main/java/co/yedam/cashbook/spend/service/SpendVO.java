package co.yedam.cashbook.spend.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;

@Data
public class SpendVO {
	private int outMoney;
	private String outGroup;
	private String outMemo;
	private Date outDate;
	private int outId;
	private String outName;
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");	
		System.out.println("+--------------------------------------------------+");
		System.out.println("  [ 금액 ] " + outMoney + "원\t" + "\t[ 분류 ]\t" + outGroup);
		System.out.println("  [ 지출일 ] " + sdf.format(outDate)+"\t[ 메모 ]\t" + outMemo);
		return null;
	}
	public String simpleString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");	
		System.out.println("+--------------------------------------------------+");
		System.out.println("  [ 금액 ] " + outMoney + "원" + "\t[ 지출일 ] " + sdf.format(outDate));
		return null;
	}
}
