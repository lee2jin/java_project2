package co.yedam.cashbook;

import java.util.Scanner;

import co.yedam.cashbook.common.SHA256;
import co.yedam.cashbook.income.menu.IncomeManager;
import co.yedam.cashbook.spend.menu.SpendManager;
import co.yedam.cashbook.user.menu.UserManager;
import co.yedam.cashbook.user.service.UserService;
import co.yedam.cashbook.user.service.UserVO;
import co.yedam.cashbook.user.serviceImpl.UserServiceImpl;

public class MainMenu {
	private UserService dao = new UserServiceImpl();
	private IncomeManager im = new IncomeManager();
	private SpendManager sm = new SpendManager();
	private UserManager um = new UserManager();
	private Scanner sc = new Scanner(System.in);
	private SHA256 sha256 = new SHA256();
	private UserVO vo = new UserVO();
	
	private void loginTitle() {
		System.out.println("====================================================");
		System.out.println("|                 ★☆ 환 영 합 니 다 ☆★                |");
		System.out.println("====================================================");
		System.out.println("|              1. 로그인        2. 회원가입             |");
		System.out.println("+--------------------------------------------------+");
		System.out.print("  ▶ 메뉴를 선택하세요 >> ");
	}
	

	public void run() {
		boolean b = false;
		do {
			loginTitle();
			int menu = sc.nextInt();
			sc.nextLine();
			switch (menu) {
			case 1:
				b = login();
				break;
			case 2:
				userInsert();
				break;
			}
		} while(!b);
			menu(vo.getUserId());
	}
	
	
	// --------------------------------------------- ★ 로그인
	private boolean login() {
		boolean b = false;
		while (!b) {
			System.out.println("  ▶ 아이디를 입력하세요.");
			vo.setUserId(sc.nextLine());
			System.out.println("  ▶ 비밀번호를 입력하세요.");
			vo.setUserPassword(sc.nextLine());
			vo.setUserPassword(sha256.encrypt(vo.getUserPassword()));
			vo = dao.userSelect(vo);
			if (vo != null) {
				System.out.println("  ★☆ " + vo.getUserName() + "님 환영합니다! ☆★");
				b = true;
			} else {
				System.out.println("  ※ 정보가 일치하지 않습니다.");
			}
		}return b;
	}
	// --------------------------------------------- ★ 회원 가입
	private void userInsert() {
		System.out.println("  ▶ 아이디를 입력하세요.");
		vo.setUserId(sc.nextLine());
		System.out.println("  ▶ 비밀번호를 입력하세요.");
		vo.setUserPassword(sc.nextLine());
		System.out.println("  ▶ 이름을 입력하세요.");
		vo.setUserName(sc.nextLine());

		vo.setUserPassword(sha256.encrypt(vo.getUserPassword()));

		int n = dao.userInsert(vo);

		if (n != 0) {
			System.out.println("  ※ 회원가입 완료");
		} else {
			System.out.println("  ※ 회원가입 실패! 다시 입력하세요.");
		}
	}
	

	private void title() {
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+                    《 가 계 부 》                    +");
		System.out.println("+--------------------------------------------------+");
		System.out.println("+     1. 수입 관리   2. 지출 관리   3 유저관리   4. 종 료    +");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.print("  ▶ 메뉴를 선택하세요 >> ");
	}

	public void menu(String id) {
		boolean b = false;
		do {
			title();
			int key = sc.nextInt();
			sc.nextLine();
			switch (key) {
			case 1:
				im.run(id);
				break;
			case 2:
				sm.run(id);
				break;
			case 3:
				um.run();
				break;
			case 4:
				System.out.println("  ★☆ 프로그램을 종료합니다! ☆★");
				b = true;
				sc.close();
				break;
			}
		} while (!b);

	}
}
