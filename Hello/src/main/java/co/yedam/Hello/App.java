package co.yedam.Hello;

import co.yedam.Hello.product.menu.ProductManeger;

//import java.util.ArrayList;
//import java.util.List;
//
//import co.yedam.Hello.product.service.ProductService;
//import co.yedam.Hello.product.service.ProductVO;
//import co.yedam.Hello.product.serviceImpl.ProductServiceImpl;

public class App 
{
    public static void main( String[] args )
    {
//        ProductService dao = new ProductServiceImpl();
//        List<ProductVO> products = new ArrayList<ProductVO>();
//        
//        products = dao.productSelectList();
//        
//        for(ProductVO v : products) {
//        	v.toString();
//        }
    	
    	ProductManeger menu = new ProductManeger();
    	menu.run();
   }
}
