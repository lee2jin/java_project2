package co.yedam.cashbook;

import java.util.Scanner;

import co.yedam.cashbook.income.menu.IncomeManager;
import co.yedam.cashbook.spend.menu.SpendManager;

public class MainMenu {

	private IncomeManager im = new IncomeManager();
//	private IncomeService idao = new IncomeServiceImpl();
	private SpendManager sm = new SpendManager();
//	private SpendService sdao = new SpendServiceImpl();

	private Scanner sc = new Scanner(System.in);

	private void title() {
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+                    《 가 계 부 》                   +");
		System.out.println("+--------------------------------------------------+");
		System.out.println("+    1. 수입 관리   2. 지출 관리   3. 잔액확인   4. 종료   +");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.print("메뉴를 선택하세요 >> ");
	}

	public void run() {
		boolean b = false;
		
		do {
			title();
			int key = sc.nextInt();
			sc.nextLine();
			switch (key) {
			case 1:
				im.run();
				break;
			case 2:
				sm.run();
				break;
			case 4:
				b = true;
				sc.close();
				System.out.println("프로그램을 종료 합니다..");
				break;
			}
		} while (!b);

	}
}
