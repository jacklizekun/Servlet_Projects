package domain;

import java.util.ArrayList;
import java.util.List;

public class Cart02 {

	private double total;
	private List<CartItem> list=new ArrayList();
	
	
	public void addCartItemToCart(CartItem cartItem){
		boolean flag=false;
		CartItem old=null;
		for (CartItem cartItem2 : list) {
			if(cartItem2.getProduct().getPid().equals(cartItem.getProduct().getPid())){
				flag=true;
				old=cartItem2;
			}
		}
		if(flag==false){
			list.add(cartItem);
		}else{
			old.setNum(old.getNum()+cartItem.getNum());
		}
		
		
	}

	public void removeCartItem(String pid){
		for (CartItem cartItem : list) {
			if(cartItem.getProduct().getPid().equals(pid)){
			}
		}
		
		
	}
	public void clearCart(){
		list.clear();
	}
}
