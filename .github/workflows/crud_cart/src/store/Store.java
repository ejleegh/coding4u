package store;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import crud.CrudManager;
import manager.Factory;

public class Store {
	public static void main(String args[]) {
		Store store = new Store();
		store.doit();
	}
	static Scanner keyin = new Scanner(System.in);
	static CrudManager<Item> itemMgr = new CrudManager<>(keyin);
	static CrudManager<Order> orderMgr = new CrudManager<>(keyin);
	HashMap<String, Order> orders = new HashMap<>();
	ItemFac itemFac = new ItemFac();
	void init() {
		itemMgr.readAll("items.txt", itemFac);
		orderMgr.readAll("orders.txt", new OrderFac());
		Order.store = this;
		for (Order o : orderMgr.getList())
			orders.put(o.userid, o);
	}
	class ItemFac implements Factory<Item> {
		public Item create() {
			return new Item();
		}
	}
	class OrderFac implements Factory<Order> {
		public Order create() {return new Order(); }
	}
	public static Item findItem(String kwd) {
		return itemMgr.find(kwd);
	}
	Order findOrder(String kwd) {
		return orderMgr.find(kwd);
	}
	public void shopping(Scanner keyin) {
		
	}
	public void searchUsersForItem() {
		System.out.println("아이템아이디 (0이면 종료) : ");
		String indx = null;
		indx = keyin.next();
		Item it = findItem(indx);
		if (it == null) {
			System.out.println("없는 번호입니다.");
			return;
		}
		it.print();
		System.out.println();
		HashSet<String> users = getUsersForItem(it);
		System.out.println("이 상품의 주문자 : " + users);
	}
	static HashSet<String> getUsersForItem(Item it) {
		HashSet<String> users = new HashSet<>();
		for (Order o : orderMgr.getList())
			if (o.count(it) > 0)
				users.add(o.getName());		
		return users;
	}
	int count(String uid, Item item) {
		Order ord = orderMgr.find(uid);
		if (ord == null) return 0;
		return ord.count(item);
	}
	void doit() {
		init();
		int sel = 0;
		while (true) {
		System.out.println("(1) 사용자메뉴   (2) 관리자메뉴  (0) 종료");
		sel = keyin.nextInt();
		keyin.nextLine();
		if (sel == 1)
			menu();
		else if (sel == 2)
			doCrud();
		else break;
		}
		System.out.println("Bye!");
	}
	void menu() {
		while (true) {
			System.out.print("\n(1) 전체상품  (2) 상품검색  (3) 주문보기  (4) 주문추가   (5) 주문변경   (0) 종료 ");
			int menu = keyin.nextInt();
			if (menu == 0) break;
			switch(menu) {
			case 1: itemMgr.printAll(); break;
			case 2: searchItem(); break;
			case 3: orderMgr.printAll(); break;
			case 4: orderMgr.addOne(new OrderFac()); break;
			case 5: orderMgr.updateOne(); break;
			default: break;
			}
		}
	}
	void searchItem() {
		System.out.print("검색할 상품 키워드 : ");
		String kwd = keyin.next();
		for (Item it : itemMgr.getList()) {
			if (it.compare(kwd))
				it.print();
		}
	}
	public void doCrud() {
		int m = 0;
		while (true) {
			System.out.println("(1) 상품추가   (2) 상품삭제  (3) 상품수정   (4) 상품별구매자검색  (0) 종료");
			m = keyin.nextInt();
			keyin.nextLine();
			if (m == 0) break;
			if (m == 1) {
				itemMgr.addOne(itemFac);
			} else if (m == 2) {
				itemMgr.removeOne();
			} else if (m == 3) {
				itemMgr.updateOne();
			} else if (m == 4) {
				searchUsersForItem();
			}
		}
	}	
}