package co.yedam.cashbook.income.menu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import co.yedam.cashbook.income.service.IncomeService;
import co.yedam.cashbook.income.service.IncomeVO;
import co.yedam.cashbook.income.serviceImpl.IncomeServiceImpl;

public class IncomeManager {
	private Scanner sc = new Scanner(System.in);
	private IncomeService dao = new IncomeServiceImpl();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");

	private void mainTitle() {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+    1. 소득추가         2. 수정하기        3. 삭제하기    +");
		System.out.println("+-------------------------------------------------+");
		System.out.println("+    4. 전체 소득 목록    5. 일별 소득 목록    6.메인메뉴     +");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.print("메뉴를 선택하세요 >> ");
	}

	public void run() {
		boolean b = false;
		do {
			mainTitle();
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				incomeInsert();
				break;
			case 2:
				incomeUpdate();
				break;

			case 3:
				incomeDelete();
				break;

			case 4:
				incomeList();
				break;
			case 5:

				break;
			case 6:
				b = true;
				break;
			}
		} while (!b);

	}
	
	
	
	// --------------------------------------------- 전체목록 조회
	private void incomeList() {
		List<IncomeVO> incomes = new ArrayList<IncomeVO>();

		incomes = dao.IncomeSelectList();
		for (IncomeVO i : incomes) {
			i.toString();
		}
		
	}

	// --------------------------------------------- 삭제하기
	private void incomeDelete() {
		IncomeVO vo = new IncomeVO();
		System.out.println("삭제 날짜 입력");
		vo.setInDate(sc.nextLine()); // ★★★★★★★★★★★이거 진짜 어케해요

		int n = dao.IncomeDelete(vo);
		if (n != 0) {
			System.out.println("삭제 완료");
		} else {
			System.out.println("삭제 실패");
		}
	}

	// --------------------------------------------- 수정하기
	private void incomeUpdate() {
		IncomeVO vo = new IncomeVO();
		System.out.println("수정 날짜를 입력하세요");
		vo.setInDate(sc.nextLine());
		submenu();
		int key = sc.nextInt();
		sc.nextLine();
		switch (key) {
		case 1:
			System.out.println("금액을 입력하세요");
			vo.setMoneyIn(sc.nextInt());
			sc.nextLine();
			System.out.println("소득 그룹을 입력하세요");
			vo.setInGroup(sc.nextLine());
//			System.out.println("소득 날짜를 입력하세요");
//			vo.setInDate(sc.nextLine());
			break;
		case 2:
			System.out.println("금액을 입력하세요");
			vo.setMoneyIn(sc.nextInt());
			sc.nextLine();
			break;
		case 3:
			System.out.println("소득 그룹을 입력하세요");
			vo.setInGroup(sc.nextLine());
			break;
		case 4:
//			System.out.println("소득 날짜를 입력하세요");
//			vo.setInDate(sc.nextLine());
			break;
		}
		int n = dao.IncomeUpdate(vo);
		if (n != 0) {
			System.out.println("수정 완료");
		} else {
			System.out.println("수정 실패");
		}

	}

	private void submenu() {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+   1. 전체 수정  2. 금액 수정   3. 그룹 수정  4. 날짜 수정   +");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.print("메뉴를 선택하세요 >> ");
	}

	// --------------------------------------------- 추가하기
	private void incomeInsert() {

		IncomeVO vo = new IncomeVO();
		System.out.print("금액을 입력하세요 >> ");
		vo.setMoneyIn(sc.nextInt());
		sc.nextLine();
		System.out.print("소득 그룹을 선택하세요 >> ");
		vo.setInGroup(sc.nextLine());
//		System.out.print("소득 날짜를 선택하세요( >> ");
//		 vo.setInDate(sc.nextLine());			
		System.out.print("소득 내용을 입력하세요(선택사항) >> ");
		vo.setInMemo(sc.nextLine());

		int n = dao.IncomeInsert(vo);
		if (n != 0) {
			System.out.println("소득 저장 완료");
		} else {
			System.out.println("소득 저장 실패");
		}

	}
}
