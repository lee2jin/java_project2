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
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");	
		System.out.println("+--------------------------------------------------+");
		System.out.println("  ■ 금액: " + moneyIn + "원" + "    ■ 분류: " + inGroup);
		System.out.print("  ■ 입금일: " + sdf.format(inDate));
		System.out.println("    ■ 메모: " + inMemo);
		return null;
	}
		
}
