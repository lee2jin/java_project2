package co.yedam.cashbook.income.service;

import java.util.List;

public interface IncomeService {
	List<IncomeVO> IncomeSelectList(); // 전체
	int IncomeSelect(IncomeVO vo); // 한건
	int IncomeInsert(IncomeVO vo); //등록 
	int IncomeDelete(IncomeVO vo); //삭제
	int IncomeUpdate(IncomeVO vo); //수정

}
