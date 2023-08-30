package co.yedam.cashbook.income.menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.yedam.cashbook.income.service.IncomeService;
import co.yedam.cashbook.income.service.IncomeVO;
import co.yedam.cashbook.income.serviceImpl.IncomeServiceImpl;

public class IncomeManager {
	private Scanner sc = new Scanner(System.in);
	private IncomeService dao = new IncomeServiceImpl();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");

	private void mainTitle() {
		System.out.println("====================================================");
		System.out.println("|                    수 입 관 리                      |");
		System.out.println("====================================================");
		System.out.println("|     1. 추 가         2. 수 정         3. 삭 제       |");
		System.out.println("+--------------------------------------------------+");
		System.out.println("|     4. 전체 목록      5. 일별 목록       6.전체 합계     |");
		System.out.println("+--------------------------------------------------+");
		System.out.println("|                     7.홈으로                       |");
		System.out.println("+--------------------------------------------------+");
		System.out.print("  ▶ 메뉴를 선택하세요! >> ");
	}

	public void run(String id) {
		boolean b = false;
		do {
			mainTitle();
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				incomeInsert(id);
				break;
			case 2:
				incomeUpdate(id);
				break;

			case 3:
				incomeDelete(id);
				break;

			case 4:
				incomeList(id);
				break;
			case 5:
				incomeSelect(id);
				break;
			case 6:
				incomeSum(id);
				break;

			case 7:
				b = true;
				break;
			}
		} while (!b);

	}

	// --------------------------------------------- ★ 전체 금액 합계
	private void incomeSum(String id) {
		IncomeVO vo = new IncomeVO();
		int sum = 0;
		vo.setInName(id);
		List<IncomeVO> incomes = new ArrayList<IncomeVO>();
		incomes = dao.incomeSelectList(vo);
		for (IncomeVO i : incomes) {
			sum += i.getMoneyIn();
		}
		System.out.println("  ▷ 전체 소득 금액은 " + sum + "원 입니다.");
	}

	// --------------------------------------------- ★ 날짜(한건) 조회
	private void incomeSelect(String id) {
		IncomeVO vo = new IncomeVO();
		vo.setInName(id);
		while (true) {
			System.out.print("  ▶ 조회 날짜를 입력하세요.");
			String date = sc.nextLine();
			try {
				vo.setInDate(sdf.parse(date));

				vo = dao.incomeSelect(vo);

				vo.toString();
				break;

			} catch (ParseException e) {
				System.out.println("  ※ 조회 실패! 다시 입력하세요");
			}
		}
	}

	// --------------------------------------------- ★ 전체목록 조회
	private void incomeList(String id) {
		IncomeVO vo = new IncomeVO();
		List<IncomeVO> incomes = new ArrayList<IncomeVO>();
		vo.setInName(id);
		incomes = dao.incomeSelectList(vo);
		for (IncomeVO i : incomes) {
			i.simpleString();

		}
	}

	// --------------------------------------------- ★ 삭제하기
	private void incomeDelete(String id) {
		
		IncomeVO vo = new IncomeVO();
		vo.setInName(id);
		while (true) {
			System.out.print("  ▶ 삭제 날짜 입력를 입력하세요(yyMMdd)");
			String date = sc.nextLine();
			try {
				vo.setInDate(sdf.parse(date));
				int n = dao.incomeDelete(vo);
				if (n != 0) {
					System.out.println("  ※ 삭제 완료");
					break;
				}
			} catch (ParseException e) {
				System.out.println("  ※ 삭제 실패! 다시 입력하세요");
			}
		}
	}

	// --------------------------------------------- ★ 수정하기
	private void incomeUpdate(String id) {
		IncomeVO vo = new IncomeVO();
		vo.setInName(id);
		System.out.println("  ▶ 수정 날짜를 입력하세요(yyMMdd)");
		String date = sc.nextLine();
		try {
			vo.setInDate(sdf.parse(date));
			submenu(); // 전체, 금액, 그룹, 날짜 수정

		} catch (ParseException e) {
			System.out.println("  ※ 날짜를 다시 입력하세요");
		}
		int key = sc.nextInt();
		sc.nextLine();
		switch (key) {
		case 1: // 전체 수정
			System.out.println("  ▶ 수정할 금액을 입력하세요");
			vo.setMoneyIn(sc.nextInt());
			sc.nextLine();
			group();
			int no = sc.nextInt();
			sc.nextLine();

			switch (no) {
			case 1:
				vo.setInGroup("월급");
				break;
			case 2:
				vo.setInGroup("용돈");
				break;
			case 3:
				vo.setInGroup("기타");
				break;
			}
			break;
		case 2: // 금액 수정
			System.out.println("  ▶ 금액을 입력하세요");
			vo.setMoneyIn(sc.nextInt());
			sc.nextLine();
			break;
		case 3: // 그룹 수정
			group();
			no = sc.nextInt();
			sc.nextLine();

			switch (no) {
			case 1:
				vo.setInGroup("월급");
				break;
			case 2:
				vo.setInGroup("용돈");
				break;
			case 3:
				vo.setInGroup("기타");
				break;
			}
			break;
		}
		int n = dao.incomeUpdate(vo);
		if (n != 0) {
			System.out.println("  ※ 수정 완료");
		} else {
			System.out.println("  ※ 수정 실패");
		}

	}

	private void submenu() {
		System.out.println("====================================================");
		System.out.println("+     1. 전체 수정      2. 금액 수정      3. 그룹 수정     +");
		System.out.println("====================================================");
		System.out.print("  ▶ 메뉴를 선택하세요 >> ");
	}

	private void group() {
		System.out.println("====================================================");
		System.out.println("+        1. 월 급       2. 용 돈      3. 기 타         +");
		System.out.println("====================================================");
		System.out.print("  ▶ 그룹을 선택하세요 >> ");
	}

	// --------------------------------------------- ★ 추가하기
	private void incomeInsert(String id) {
		IncomeVO vo = new IncomeVO();

		System.out.print("  ▶ 금액을 입력하세요 >> "); // 금액입력
		vo.setMoneyIn(sc.nextInt());
		sc.nextLine();

		group();
		int no = sc.nextInt();
		sc.nextLine();

		switch (no) {
		case 1:
			vo.setInGroup("월급");
			break;
		case 2:
			vo.setInGroup("용돈");
			break;
		case 3:
			vo.setInGroup("기타");
			break;
		}

		while (true) { // 날짜 입력
			System.out.print("  ▶ 소득 날짜를 입력하세요(yyMMdd)");
			String date = sc.nextLine();

			try {
				vo.setInDate(sdf.parse(date));
				break;
			} catch (ParseException e) {
				System.out.println("  ※ 날짜를 다시 입력하세요(yyMMdd)");
				e.printStackTrace();
			}
		}

		System.out.print("  ▶ 소득 내용을 입력하세요(선택사항) >> ");
		vo.setInMemo(sc.nextLine());
		vo.setInName(id);
		if (dao.incomeInsert(vo) != 0) {
			System.out.println("  ※ 저장 완료");
		} else {
			System.out.println("  ※ 저장 실패");
		}

	}

}
