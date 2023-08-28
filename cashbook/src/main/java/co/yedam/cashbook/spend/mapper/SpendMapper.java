package co.yedam.cashbook.spend.mapper;

import java.util.List;

public interface SpendMapper {
	List<SpendVO> spendSelectList(); // 전체
	int spendSelect(SpendVO vo); // 한건
	int spendInsert(SpendVO vo); //등록 
	int spendDelete(SpendVO vo); //삭제
	int spendUpdate(SpendVO vo); //수정

}
