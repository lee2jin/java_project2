package co.yedam.cashbook.spend.menu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.yedam.cashbook.MainMenu;
import co.yedam.cashbook.income.service.IncomeVO;
import co.yedam.cashbook.spend.service.SpendService;
import co.yedam.cashbook.spend.service.SpendVO;
import co.yedam.cashbook.spend.serviceImpl.SpendServiceImpl;

public class SpendManager {
	private Scanner sc = new Scanner(System.in);
	private SpendService dao = new SpendServiceImpl();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
//	private int no;

	private void mainTitle() {
		System.out.println("====================================================");
		System.out.println("|                    지 출 관 리                      |");
		System.out.println("====================================================");
		System.out.println("|     1. 추 가         2. 수 정         3. 삭 제       |");
		System.out.println("+--------------------------------------------------+");
		System.out.println("|     4. 전체 목록      5. 일별 목록      6. 전체 합계     |");
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
				spendInsert(id);
				break;
			case 2:
				spendUpdate(id);
				break;

			case 3:
				spendDelete(id);
				break;

			case 4:
				spendList(id);
				break;
			case 5:
				spendSelect(id);
				break;
			case 6:
				spendSum(id);
				break;
			case 7:
				b=true;
				break;
			}
		} while (!b);

	}
	
	// --------------------------------------------- ★ 전체 지출 합계
	private void spendSum(String id) {
		SpendVO vo = new SpendVO();
		int sum = 0;
		vo.setOutName(id);
		List<SpendVO> spends = new ArrayList<SpendVO>();
		spends = dao.spendSelectList(vo);
		for (SpendVO i : spends) {
			sum += i.getOutMoney();
		}
		System.out.println("  ▷ 전체 지출 금액은 " + sum + "원 입니다.");
	}

	// --------------------------------------------- ★ 날짜(한건) 조회
	private void spendSelect(String id) {
		SpendVO vo = new SpendVO();
		vo.setOutName(id);
		while (true) {
			System.out.print("  ▶ 조회 날짜를 입력하세요.");
			String date = sc.nextLine();
			try {
				vo.setOutDate(sdf.parse(date));

				vo = dao.spendSelect(vo);

				vo.toString();
				break;

			} catch (ParseException e) {
				System.out.println("  ※ 조회 실패! 다시 입력하세요");
			}
		}
	}

	// --------------------------------------------- ★ 전체목록 조회
	private void spendList(String id) {
		SpendVO vo = new SpendVO();
		List<SpendVO> spends = new ArrayList<SpendVO>();
		vo.setOutName(id);
		spends = dao.spendSelectList(vo);
		for (SpendVO i : spends) {
			i.simpleString();
		}
	}

	// --------------------------------------------- ★ 삭제하기
	private void spendDelete(String id) {
		SpendVO vo = new SpendVO();
		vo.setOutName(id);
		while (true) {
			System.out.print("  ▶ 삭제 날짜 입력를 입력하세요(yyMMdd)");
			String date = sc.nextLine();
			try {
				vo.setOutDate(sdf.parse(date));
				int n = dao.spendDelete(vo);
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
	private void spendUpdate(String id) {
		SpendVO vo = new SpendVO();
		vo.setOutName(id);
		System.out.println("  ▶ 수정 날짜를 입력하세요(yyMMdd)");
		String date = sc.nextLine();
		try {
			vo.setOutDate(sdf.parse(date));
			submenu(); // 전체, 금액, 그룹, 날짜 수정

		} catch (ParseException e) {
			System.out.println("  ※ 날짜를 다시 입력하세요");
		}
		int key = sc.nextInt();
		sc.nextLine();
		switch (key) {
		case 1: // 전체 수정
			System.out.println("  ▶ 수정할 금액을 입력하세요");
			vo.setOutMoney(sc.nextInt());
			sc.nextLine();
			group();
			int no = sc.nextInt();
			sc.nextLine();
			
			switch (no) {
			case 1:
				vo.setOutGroup("생활");
				break;
			case 2:
				vo.setOutGroup("교육");
				break;
			case 3:
				vo.setOutGroup("의료");
				break;
			case 4:
				vo.setOutGroup("기타");
				break;
			}
			break;
			
		case 2: // 금액 수정
			System.out.println("  ▶ 금액을 입력하세요");
			vo.setOutMoney(sc.nextInt());
			sc.nextLine();
			break;
		case 3: // 그룹 수정
			group();
			no = sc.nextInt();
			sc.nextLine();
			switch (no) {
			case 1:
				vo.setOutGroup("생활");
				break;
			case 2:
				vo.setOutGroup("교육");
				break;
			case 3:
				vo.setOutGroup("의료");
				break;
			case 4:
				vo.setOutGroup("기타");
				break;
			}
			break;
		}
		int n = dao.spendUpdate(vo);
		if (n != 0) {
			System.out.println("  ※ 수정 완료");
		} else {
			System.out.println("  ※ 수정 실패");
		}
	}

	private void submenu() {
		System.out.println("====================================================");
		System.out.println("+     1. 전체 수정     2. 금액 수정     3. 그룹 수정       +");
		System.out.println("====================================================");
		System.out.print("  ▶ 메뉴를 선택하세요 >> ");
	}

	// --------------------------------------------- ★ 추가하기
	private void spendInsert(String id) {
		SpendVO vo = new SpendVO();

//		vo.setOutId(no);

		System.out.print("  ▶ 금액을 입력하세요 >> ");
		vo.setOutMoney(sc.nextInt());
		sc.nextLine();

		group();
		int no = sc.nextInt();
		sc.nextLine();
		switch (no) {
		case 1:
			vo.setOutGroup("생활");
			break;
		case 2:
			vo.setOutGroup("교육");
			break;
		case 3:
			vo.setOutGroup("의료");
			break;
		case 4:
			vo.setOutGroup("기타");
			break;
		}
		
		
		while (true) {
			System.out.print("  ▶ 지출 날짜를 입력하세요(yyMMdd)");
			String date = sc.nextLine();

			try {
				vo.setOutDate(sdf.parse(date));
				break;
			} catch (ParseException e) {
				System.out.println("  ※ 날짜를 다시 입력하세요(yyMMdd)");
				e.printStackTrace();
			}
		}

		System.out.print("  ▶ 지출 내용을 입력하세요(선택사항) >> ");
		vo.setOutMemo(sc.nextLine());
		vo.setOutName(id);
		if (dao.spendInsert(vo) != 0) {
			System.out.println("  ※ 저장 완료");
//			no++;
		} else {
			System.out.println("  ※ 저장 실패");
		}
	}

	private void group() {
		System.out.println("====================================================");
		System.out.println("+     1. 생 활    2. 의 료    3. 교 육    4. 기 타      +");
		System.out.println("====================================================");
		System.out.print("  ▶ 그룹을 선택하세요 >> ");
	}
}
