package store;

import java.util.HashMap;
import java.util.Scanner;

import crud.Crudable;
import manager.Factory;
import manager.Managable;
import manager.Manager;

public class Order implements Managable, Crudable {
	String userid;
	static Store store = null;
	HashMap<String, Integer> map = new HashMap<>();
	int count(Item it) {
		Integer n = map.get(it.itemId);
		if (n == null) 
			return 0;
		return n;
	}
	@Override
	public void read(Scanner fileIn) {
		// TODO Auto-generated method stub
		String id = null;
		userid = fileIn.next();
		while (true) {
			id = fileIn.next();
			if (id.equals("0")) break;
			map.put(id, fileIn.nextInt());
		}				
	}
	@Override
	public void print() {
		// TODO Auto-generated method stub
		System.out.println(userid + " 주문");
		Item item = null;
		int subtotal = 0, total = 0;
		for (String id : map.keySet()) {
			subtotal = 0;
			item = store.findItem(id);
			if (item==null)
				continue;
			System.out.printf("   %s \t%d개 * %d원%n", item.getName(), map.get(id), item.getPrice());
			subtotal += item.getPrice() * map.get(id);
			total += subtotal;
		}
		System.out.println(" -> 계 : "+total);
		
	}
	@Override
	public boolean compare(String kwd) {
		// TODO Auto-generated method stub
		return userid.equals(kwd);
	}
	@Override
	public String getName() {
		return userid;
	}
	@Override
	public void readOnLine(Scanner keyin) {
		// TODO Auto-generated method stub
		String tmp = null;
		while (true) {
			System.out.print("  >> 사용자 아이디 : ");
			tmp = keyin.next();
			Order ord = store.findOrder(tmp);
			if (ord != null) {
				System.out.println("이미 주문이 있는 아이디입니다. 수정 메뉴로 이동합니다.");
				ord.update(keyin);
				return;
			}
			break;
		}
		userid = tmp;
		while (addItem(keyin)) 
			;
		System.out.print("  신규추가 : ");
		print();
	}
	private boolean addItem(Scanner keyin) {
		System.out.print("  >> 메뉴아이디 : ");
		String id = keyin.next();
		if (id.equals("0")) return false;
		System.out.print("  >> 개수 : ");
		int cc = keyin.nextInt();
		if (cc == 0 && map.get(id) > 0)
			map.remove(id);
		else if (map.get(id) > 0) {
			map.put(id,  map.get(id)+cc);
		} else
			map.put(id, cc);
		return true;
	}
	@Override
	public boolean checkRemove(Scanner keyin) {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public void update(Scanner keyin) {
		// TODO Auto-generated method stub
		String tmp = null;
		boolean menu = true;
		while (menu) {
			System.out.println(map);
			menu = addItem(keyin);
		}
		print();
	}
	@Override
	public boolean compare(int n) {
		// TODO Auto-generated method stub
		return false;
	}
}
