package co.yedam.cashbook.spend.service;

import java.util.List;

public interface SpendService {
	List<SpendVO> spendSelectList(); // 전체
	SpendVO spendSelect(SpendVO vo); // 한건
	int spendInsert(SpendVO vo); //등록 
	int spendDelete(SpendVO vo); //삭제
	int spendUpdate(SpendVO vo); //수정
}
