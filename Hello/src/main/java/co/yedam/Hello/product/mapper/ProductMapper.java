package co.yedam.Hello.product.mapper;

import java.util.List;
import co.yedam.Hello.product.service.ProductVO;

public interface ProductMapper {

	
		List<ProductVO> productSelectList(); //R 전체조회
		ProductVO productSelect(ProductVO vo); //R 하나의 제품 조회
		int productInsert(ProductVO vo); //C 제품 등록
		int productDelete(ProductVO vo); //D 제품 삭제	
		int productUpdate(ProductVO vo); //U 제품 변경
		
	
	
}
