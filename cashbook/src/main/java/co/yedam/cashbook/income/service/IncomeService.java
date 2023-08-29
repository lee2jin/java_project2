package co.yedam.cashbook.income.service;

import java.util.List;

public interface IncomeService {
	List<IncomeVO> incomeSelectList(); // 전체
	IncomeVO incomeSelect(IncomeVO vo); // 한건
	int incomeInsert(IncomeVO vo); //등록 
	int incomeDelete(IncomeVO vo); //삭제
	int incomeUpdate(IncomeVO vo); //수정
}
