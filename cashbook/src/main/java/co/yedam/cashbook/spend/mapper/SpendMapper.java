package co.yedam.cashbook.spend.mapper;

import java.util.List;
import co.yedam.cashbook.spend.service.SpendVO;


public interface SpendMapper {
	List<SpendVO> spendSelectList(); // 전체

	SpendVO spendSelect(SpendVO vo); // 한건

	SpendVO spendSum(); // 통계

	int spendInsert(SpendVO vo); // 등록

	int spendDelete(SpendVO vo); // 삭제

	int spendUpdate(SpendVO vo); // 수정
}
