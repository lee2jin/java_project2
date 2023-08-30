package co.yedam.cashbook.user.menu;

import java.util.List;
import java.util.Scanner;

import co.yedam.cashbook.common.SHA256;
import co.yedam.cashbook.user.service.UserService;
import co.yedam.cashbook.user.service.UserVO;
import co.yedam.cashbook.user.serviceImpl.UserServiceImpl;

public class UserManager {
	private Scanner sc = new Scanner(System.in);
	private UserService dao = new UserServiceImpl();
	private SHA256 sha256 = new SHA256();

	private void Title() {
		System.out.println("====================================================");
		System.out.println("|                    유 저 관 리                      |");
		System.out.println("====================================================");
		System.out.println("|        1. 수 정       2. 삭 제       3. 리스트        |");
		System.out.println("+--------------------------------------------------+");
		System.out.println("|                     9.홈으로                       |");
		System.out.println("+--------------------------------------------------+");
		System.out.print("  ▶ 메뉴를 선택하세요! >> ");
	}

	public void run() {
		boolean b = false;
		do {
			Title();
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 1:
				userUpdate();
				break;
			case 2:
				userDelete();
				break;
			case 3:
				userList();
				break;
			case 9:
				b = true;
				break;
			}
		} while (!b);

	}

	// --------------------------------------------- ★ 리스트
	private void userList() {
		List<UserVO> users = dao.userSelectList();
		System.out.println("====================================================");
		System.out.println("|                   유 저 리 스 트                     |");
		System.out.println("====================================================");
		for (UserVO u : users) {
			System.out.print("  아이디: " + u.getUserId() + "\t이름: " + u.getUserName());
		}
	}

	// --------------------------------------------- ★ 수정
	private void userUpdate() {
		UserVO vo = new UserVO();
		System.out.println("  ▶ 수정할 비밀번호를 입력하세요");
		vo.setUserPassword(sc.nextLine());
		System.out.println("  ▶ 수정할 이름을 입력하세요");
		vo.setUserName(sc.nextLine());
	}

	// --------------------------------------------- ★ 삭제
	private void userDelete() {
		boolean b = false;
		while (!b) {
			UserVO vo = new UserVO();
			System.out.println("  ▶ 아이디를 입력하세요.");
			vo.setUserId(sc.nextLine());
			System.out.println("  ▶ 비밀번호를 입력하세요.");
			vo.setUserPassword(sc.nextLine());
			vo.setUserPassword(sha256.encrypt(vo.getUserPassword()));
			int n = dao.userDelete(vo);
			if (n != 0) {
				System.out.println("  ※ 삭제 완료");
			} else {
				System.out.println("  ※ 삭제 실패! 다시 입력하세요");
			}
		}
	}
}
