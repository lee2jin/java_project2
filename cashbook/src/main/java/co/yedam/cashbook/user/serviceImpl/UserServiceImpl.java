package co.yedam.cashbook.user.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.cashbook.common.DataSource;
import co.yedam.cashbook.user.mapper.UserMapper;
import co.yedam.cashbook.user.service.UserService;
import co.yedam.cashbook.user.service.UserVO;

public class UserServiceImpl implements UserService {
	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private UserMapper map = sqlSession.getMapper(UserMapper.class);
	@Override
	public List<UserVO> userSelectList() {
		return map.userSelectList();
	}
	
	@Override
	public UserVO userSelect(UserVO vo) {
		return map.userSelect(vo);
	}

	@Override
	public int userInsert(UserVO vo) {
		return map.userInsert(vo);
	}

	@Override
	public int userUpdate(UserVO vo) {
		return map.userUpdate(vo);
	}

	@Override
	public int userDelete(UserVO vo) {
		return map.userDelete(vo);
	}

	
}
