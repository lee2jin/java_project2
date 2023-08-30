package co.yedam.cashbook.user.mapper;

import java.util.List;

import co.yedam.cashbook.user.service.UserVO;

public interface UserMapper {
List<UserVO> userSelectList(); // 회원 리스트
	
	UserVO userSelect(UserVO vo); // 한건리스트

	int userInsert(UserVO vo); // 가입

	int userUpdate(UserVO vo); // 수정

	int userDelete(UserVO vo); // 탈퇴
}
