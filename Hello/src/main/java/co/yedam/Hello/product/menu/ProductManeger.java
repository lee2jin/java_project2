package co.yedam.Hello.product.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.yedam.Hello.product.service.ProductService;
import co.yedam.Hello.product.service.ProductVO;
import co.yedam.Hello.product.serviceImpl.ProductServiceImpl;

public class ProductManeger { // 제품관리 메뉴
	private Scanner sc = new Scanner(System.in);
	private ProductService dao = new ProductServiceImpl();

	private void mainTitle() {
		System.out.println("============================");
		System.out.println("======== 제 품 관 리 ==========");
		System.out.println("===== 1. 제 품 전 체 조 회 =====");
		System.out.println("===== 2. 제 품 한 건 조 회 =====");
		System.out.println("======= 3. 제 품 등 록 ========");
		System.out.println("======= 4. 제 품 수 정 ========");
		System.out.println("======== 5. 제 품 삭 제 =======");
		System.out.println("======= 6. 종      료 =======");
		System.out.println("============================");
		System.out.println("= 작업번호를 선택 하세요...");
	}

	public void run() {
		boolean b = false;
		do {
			mainTitle();
			int jobCode = sc.nextInt();
			sc.nextLine();
			switch (jobCode) {
			case 1:
				// 제품 조회
				System.out.println("----------------------------");
				System.out.println("------ 제 품 전 체 목 록 ------ ");
				System.out.println("----------------------------");
				productList(); // 조회 메소드
				System.out.println("----------------------------");
				break;
			case 2:
				// 제품 한건 조회
				System.out.println("----------------------------");
				System.out.println("조회할 제품코드를 입력하세요");
				String id = sc.nextLine();
				productSelect(id);
				break;
			case 3:
				// 제품 등록
				System.out.println("------- 제 품 등 록 -------");
				productInsert();
				break;
			case 4:
				// 제품 수정
				System.out.println("------- 제 품 수 정 -------");
				productUpdate();
				break;
			case 5:
				// 제품 삭제
				System.out.println("------- 제 품 삭 제 -------");
				productDelete();
				break;
			case 6:
				// 종료
				b = true;
				System.out.println("작업이 종료됩니다.");
				sc.close();
				break;
			default:
				break;
			}
		} while (!b);
	}

	private void productDelete() {
		ProductVO vo = new ProductVO();
		System.out.println("삭제할 제품 코드 입력");
		vo.setProductId(sc.nextLine());
		
		int n = dao.productDelete(vo);
		if (n != 0) {
			System.out.println("== 제품 삭제 성공");
		} else {
			System.out.println("== 제품 삭제 실패");
		}

		
	}

	private void productUpdate() {
		// 먼저 수정할 항목 선택(기본키 제외)
		ProductVO vo = new ProductVO();
		System.out.println("수정할 제품의 코드를 입력하세요");
		vo.setProductId(sc.nextLine());
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

	private void subtitle() {
		System.out.println("===========================");
		System.out.println("== 수정할 항목 번호를 선택하세요 ==");
		System.out.println("===========================");
		System.out.println("======= 1.모 든 항 목 =======");
		System.out.println("======= 2. 제 품 명   =======");
		System.out.println("======= 3. 제 품 가 격 =======");
		System.out.println("======= 4. 제 품 모 델 =======");
		System.out.println("===========================");
		System.out.print("수정항목 선택 >> ");

	}

	private void productInsert() {
		ProductVO vo = new ProductVO();
		System.out.println("== 제품 코드를 입력하세요");
		vo.setProductId(sc.nextLine());
		System.out.println("== 제품명을 입력하세요");
		vo.setProductName(sc.nextLine());
		System.out.println("== 제품가격을 입력하세요");
		vo.setProductPrice(sc.nextInt());
		sc.nextLine();
		System.out.println("== 제품규격을 입력하세요");
		vo.setProductModel(sc.nextLine());

		int n = dao.productInsert(vo);
		if (n != 0) {
			System.out.println("== 제품 등록 성공");
		} else {
			System.out.println("== 제품 등록 실패");
		}

	}

	private void productSelect(String id) {
		ProductVO vo = new ProductVO();

		vo.setProductId(id);
		vo = dao.productSelect(vo);

		if (vo != null) {
			vo.toString();
		} else {
			System.out.println("제품코드가 존재하지 않습니다.");
		}

	}

	private void productList() {
		// 제품 전체 목록 조회
		List<ProductVO> products = new ArrayList<ProductVO>();

		products = dao.productSelectList();
		for (ProductVO v : products) {
			v.toString();
		}

	}
}
