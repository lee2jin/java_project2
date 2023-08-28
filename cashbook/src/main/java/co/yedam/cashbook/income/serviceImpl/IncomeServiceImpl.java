package co.yedam.cashbook.income.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.cashbook.common.DataSource;
import co.yedam.cashbook.income.mapper.IncomeMapper;
import co.yedam.cashbook.income.service.IncomeService;
import co.yedam.cashbook.income.service.IncomeVO;


public class IncomeServiceImpl implements IncomeService {
	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private IncomeMapper map = sqlSession.getMapper(IncomeMapper.class);
	
	@Override
	public List<IncomeVO> IncomeSelectList() {
		return map.incomeSelectList();
	}
	@Override
	public int IncomeSelect(IncomeVO vo) {
		return map.incomeSelect(vo);
			}
	@Override
	public int IncomeInsert(IncomeVO vo) {
		return map.incomeInsert(vo);
	}
	@Override
	public int IncomeDelete(IncomeVO vo) {
		return map.incomeDelete(vo);
	}
	@Override
	public int IncomeUpdate(IncomeVO vo) {
		return map.incomeUpdate(vo);
	}

}
