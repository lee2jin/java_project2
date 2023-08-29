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
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");	
		System.out.println("+--------------------------------------------------+");
		System.out.println("  ■ 금액: " + outMoney + "원" + "    ■ 분류: " + outGroup);
		System.out.print("  ■ 입금일: " + sdf.format(outDate));
		System.out.println("    ■ 메모: " + outMemo);
		return null;
	}
	
}
