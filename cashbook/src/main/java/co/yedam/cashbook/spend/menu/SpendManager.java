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
	private int no;

	private void mainTitle() {
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+    1. 지출추가         2. 수정하기       3. 삭제하기     +");
		System.out.println("+--------------------------------------------------+");
		System.out.println("+    4. 전체 지출 목록    5. 일별 지출 목록   6.메인메뉴      +");
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
				spendInsert();
				break;
			case 2:
				spendUpdate();
				break;

			case 3:
				spendDelete();
				break;

			case 4:
				spendList();
				break;
			case 5:
				spendSelect();
				break;
			case 6:
				b=true;
				break;
			}
		} while (!b);

	}

	private void spendSelect() {
		SpendVO vo = new SpendVO();
		while (true) {
			System.out.print("조회 날짜를 입력하세요.");
			String date = sc.nextLine();
			try {
				vo.setOutDate(sdf.parse(date));

				vo = dao.spendSelect(vo);

				vo.toString();
				break;

			} catch (ParseException e) {
				System.out.println("조회 실패! 다시 입력하세요");
			}
		}
	}

	// --------------------------------------------- ★ 전체목록 조회
	private void spendList() {
		List<SpendVO> spends = new ArrayList<SpendVO>();
		spends = dao.spendSelectList();
		for (SpendVO i : spends) {
			i.toString();

		}
	}

	// --------------------------------------------- ★ 삭제하기
	private void spendDelete() {
		SpendVO vo = new SpendVO();
		while (true) {
			System.out.print("삭제 날짜 입력를 입력하세요.");
			String date = sc.nextLine();
			try {
				vo.setOutDate(sdf.parse(date));
				int n = dao.spendDelete(vo);
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
	private void spendUpdate() {
		SpendVO vo = new SpendVO();
		System.out.println("수정 날짜를 입력하세요.");
		String date = sc.nextLine();
		try {
			vo.setOutDate(sdf.parse(date));
			submenu(); // 전체, 금액, 그룹, 날짜 수정

		} catch (ParseException e) {
			System.out.println("날짜를 다시 입력하세요");
		}
		int key = sc.nextInt();
		sc.nextLine();
		switch (key) {
		case 1: // 전체 수정
			System.out.println("수정할 금액을 입력하세요");
			vo.setOutMoney(sc.nextInt());
			sc.nextLine();
			System.out.println("수정할 지출 그룹을 입력하세요");
			vo.setOutGroup(sc.nextLine());
			break;
		case 2: // 금액 수정
			System.out.println("금액을 입력하세요");
			vo.setOutMoney(sc.nextInt());
			sc.nextLine();
			break;
		case 3: // 그룹 수정
			System.out.println("지출 그룹을 선택하세요");
			group();
			vo.setOutGroup(sc.nextLine());
			break;
		}
		int n = dao.spendUpdate(vo);
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

	// --------------------------------------------- ★ 추가하기
	private void spendInsert() {
		SpendVO vo = new SpendVO();

		vo.setOutId(no);

		System.out.print("금액을 입력하세요 >> ");
		vo.setOutMoney(sc.nextInt());
		sc.nextLine();
		System.out.println("지출 그룹을 선택하세요 >> ");
		group();
		vo.setOutGroup(sc.nextLine());
		while (true) {
			System.out.print("지출 날짜를 입력하세요 >> ");
			String date = sc.nextLine();

			try {
				vo.setOutDate(sdf.parse(date));
				break;
			} catch (ParseException e) {
				System.out.println("날짜를 다시 입력하세요");
				e.printStackTrace();
			}
		}

		System.out.print("지출 내용을 입력하세요(선택사항) >> ");
		vo.setOutMemo(sc.nextLine());

		if (dao.spendInsert(vo) != 0) {
			System.out.println("저장 완료");
			no++;
		} else {
			System.out.println("저장 실패");
		}
	}

	private void group() {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+      1. 생 활     2. 의 료     3. 교 육    4. 기 타      +");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.print("그룹을 선택하세요 >> ");
	}
}
