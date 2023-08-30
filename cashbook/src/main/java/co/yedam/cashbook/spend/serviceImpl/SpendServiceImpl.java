package co.yedam.cashbook.spend.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.cashbook.common.DataSource;
import co.yedam.cashbook.spend.mapper.SpendMapper;
import co.yedam.cashbook.spend.service.SpendService;
import co.yedam.cashbook.spend.service.SpendVO;

public class SpendServiceImpl implements SpendService {
	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private SpendMapper map = sqlSession.getMapper(SpendMapper.class);
	@Override
	public List<SpendVO> spendSelectList() {
		return map.spendSelectList();
	}
	@Override
	public SpendVO spendSelect(SpendVO vo) {
		return map.spendSelect(vo);
	}
	@Override
	public int spendInsert(SpendVO vo) {
		return map.spendInsert(vo);
	}
	@Override
	public int spendDelete(SpendVO vo) {
		return map.spendDelete(vo);
	}
	@Override
	public int spendUpdate(SpendVO vo) {
		return map.spendUpdate(vo);
	}
	@Override
	public SpendVO spendSum() {
		return map.spendSum();
	}
	
}
