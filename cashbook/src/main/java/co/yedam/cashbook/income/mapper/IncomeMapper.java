package co.yedam.cashbook.income.mapper;

import java.util.List;
import co.yedam.cashbook.income.service.IncomeVO;

public interface IncomeMapper {
	List<IncomeVO> incomeSelectList(); // 전체
	IncomeVO incomeSelect(IncomeVO vo); // 한건
	int incomeInsert(IncomeVO vo); //등록 
	int incomeDelete(IncomeVO vo); //삭제
	int incomeUpdate(IncomeVO vo); //수정
}
