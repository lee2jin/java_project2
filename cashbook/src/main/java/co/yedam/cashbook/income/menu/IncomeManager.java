package co.yedam.cashbook.income.menu;


import java.text.SimpleDateFormat;
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
		System.out.println("+   1. 소득추가        2. 수정하기        3. 삭제하기    +");
		System.out.println("+-------------------------------------------------+");
		System.out.println("+   4. 전체 소득 목록   5. 일별 소득 목록   6.메인메뉴     +");
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
//				incomeInsert();          //소득 추가하기			
				break;
			case 2:
				incomeUpdate();
				break;

			case 3:

				break;

			case 4:

				break;
			case 5:

				break;
			case 6:

				break;
			}
		} while (!b);

	}

	private void incomeUpdate() {
		IncomeVO vo = new IncomeVO();
		System.out.println("수정 날짜를 입력하세요");
		vo.setInDate(sc.nextLine());
		subtitle();
		int key = sc.nextInt();
		sc.nextLine();
		switch (key) {
		case 1:
			System.out.println("=== 제품명을 입력하세요 ===");
			vo.setProductName(sc.nextLine());
			System.out.println("== 제품가격을 입력하세요");
			vo.setProductPrice(sc.nextInt());
			sc.nextLine();
			System.out.println("== 제품모델을 입력하세요");
			vo.setProductModel(sc.nextLine());
			break;
		case 2:
			System.out.println("=== 제품명을 입력하세요 ===");
			vo.setProductName(sc.nextLine());
			break;
		case 3:
			System.out.println("== 제품가격을 입력하세요");
			vo.setProductPrice(sc.nextInt());
			sc.nextLine();
			break;
		case 4:
			System.out.println("== 제품모델을 입력하세요");
			vo.setProductModel(sc.nextLine());
			break;
		}
		int n = dao.productUpdate(vo);
		if (n != 0) {
			System.out.println("제품 정보 변경 성공");
		}else {
			System.out.println("제품 정보 변경 실패");
		}

	}

	private void incomeInsert() {
		
		
		IncomeVO vo = new IncomeVO();
		System.out.print("금액을 입력하세요 >> ");
		vo.setMoneyIn(sc.nextInt());sc.nextLine();
		System.out.print("소득 그룹을 선택하세요 >> ");
		vo.setInGroup(sc.nextLine());
		System.out.print("소득 날짜를 선택하세요( >> ");
		vo.setInDate(sc.nextLine());		
		System.out.print("소득 내용을 입력하세요(선택사항) >> ");
		vo.setInMemo(sc.nextLine());
		
	}
}
