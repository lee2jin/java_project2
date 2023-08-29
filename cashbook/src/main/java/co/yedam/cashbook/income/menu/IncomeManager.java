package co.yedam.cashbook.income.menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import co.yedam.cashbook.income.service.IncomeService;
import co.yedam.cashbook.income.service.IncomeVO;
import co.yedam.cashbook.income.serviceImpl.IncomeServiceImpl;

public class IncomeManager {
	private Scanner sc = new Scanner(System.in);
	private IncomeService dao = new IncomeServiceImpl();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
	private int no;

	private void mainTitle() {
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+    1. 소득추가         2. 수정하기       3. 삭제하기     +");
		System.out.println("+--------------------------------------------------+");
		System.out.println("+    4. 전체 소득 목록    5. 일별 소득 목록   6.메인메뉴      +");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
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
				incomeSelect();
				break;
			case 6:
				b = true;
				break;
			}
		} while (!b);

	}

	// --------------------------------------------- ★ 날짜(한건) 조회
	private void incomeSelect() {

		IncomeVO vo = new IncomeVO();
		while (true) {
			System.out.print("조회 날짜를 입력하세요.");
			String date = sc.nextLine();
			try {
				vo.setInDate(sdf.parse(date));

				vo = dao.incomeSelect(vo);

				vo.toString();
				break;

			} catch (ParseException e) {
				System.out.println("조회 실패! 다시 입력하세요");
			}
		}
	}

	// --------------------------------------------- ★ 전체목록 조회
	private void incomeList() {
		List<IncomeVO> incomes = new ArrayList<IncomeVO>();
		incomes = dao.incomeSelectList();
		for (IncomeVO i : incomes) {
			i.toString();

		}
	}

	// --------------------------------------------- ★ 삭제하기
	private void incomeDelete() {

		IncomeVO vo = new IncomeVO();
		while (true) {
			System.out.print("삭제 날짜 입력를 입력하세요.");
			String date = sc.nextLine();
			try {
				vo.setInDate(sdf.parse(date));
				int n = dao.incomeDelete(vo);
				if (n != 0) {
					System.out.println("삭제 완료");
					break;
				}
			} catch (ParseException e) {
				System.out.println("삭제 실패! 다시 입력하세요");
			}
		}
	}

	// --------------------------------------------- ★ 수정하기
	private void incomeUpdate() {
		IncomeVO vo = new IncomeVO();
		System.out.println("수정 날짜를 입력하세요.");
		String date = sc.nextLine();
		try {
			vo.setInDate(sdf.parse(date));
			submenu(); // 전체, 금액, 그룹, 날짜 수정

		} catch (ParseException e) {
			System.out.println("날짜를 다시 입력하세요");
		}
		int key = sc.nextInt();
		sc.nextLine();
		switch (key) {
		case 1: // 전체 수정
			System.out.println("수정할 금액을 입력하세요");
			vo.setMoneyIn(sc.nextInt());
			sc.nextLine();
			System.out.println("수정할 소득 그룹을 입력하세요");
			vo.setInGroup(sc.nextLine());
			break;
		case 2: // 금액 수정
			System.out.println("금액을 입력하세요");
			vo.setMoneyIn(sc.nextInt());
			sc.nextLine();
			break;
		case 3: // 그룹 수정
			System.out.println("소득 그룹을 선택하세요");
			group();
			vo.setInGroup(sc.nextLine());
			break;
		}
		int n = dao.incomeUpdate(vo);
		if (n != 0) {
			System.out.println("수정 완료");
		} else {
			System.out.println("수정 실패");
		}

	}

	private void submenu() {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+     1. 전체 수정      2. 금액 수정       3. 그룹 수정       +");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.print("메뉴를 선택하세요 >> ");
	}

	private void group() {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+          1. 월 급       2. 용 돈      3. 기 타          +");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.print("그룹을 선택하세요 >> ");
	}

	// --------------------------------------------- ★ 추가하기
	private void incomeInsert() {

		IncomeVO vo = new IncomeVO();
		
//		vo.setInId(no);
		if(vo.setInId(no) != null ) {
			for(int i = 1 ; i <= no+1 ; i++) {
				no = i;
			}			
		}vo.setInId(no);
		
		
		System.out.print("금액을 입력하세요 >> ");
		vo.setMoneyIn(sc.nextInt());
		sc.nextLine();
		System.out.println("소득 그룹을 선택하세요 >> ");
		group();
		vo.setInGroup(sc.nextLine());
		while (true) {
			System.out.print("소득 날짜를 입력하세요 >> ");
			String date = sc.nextLine();

			try {
				vo.setInDate(sdf.parse(date));
				break;
			} catch (ParseException e) {
				System.out.println("날짜를 다시 입력하세요");
				e.printStackTrace();
			}
		}

		System.out.print("소득 내용을 입력하세요(선택사항) >> ");
		vo.setInMemo(sc.nextLine());

		if (dao.incomeInsert(vo) != 0) {
			System.out.println("저장 완료");
			no++;
		} else {
			System.out.println("저장 실패");
		}

	}
}
