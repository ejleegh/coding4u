package oldstore;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import crud.CrudManager;
import manager.Factory;

public class SmallStore {
	public static void main(String args[]) {
		SmallStore store = new SmallStore();
		store.doit();
	}
	static Scanner keyin = new Scanner(System.in);
	static CrudManager<Item> itemMgr = new CrudManager<>(keyin);
	ItemFac itemFac = new ItemFac();
	void init() {
		itemMgr.readAll("items.txt", itemFac);
	}
	class ItemFac implements Factory<Item> {
		public Item create() {
			return new Item();
		}
	}
	public static Item findItem(String kwd) {
		return itemMgr.find(kwd);
	}
	void doit() {
		init();
		int sel = 0;
		while (true) {
		System.out.println("(1) 사용자메뉴   (2) 관리자메뉴  (0) 종료");
		sel = keyin.nextInt();
		keyin.nextLine();
		if (sel == 1)
			;//menu();
		else if (sel == 2)
			doCrud();
		else break;
		}
		System.out.println("Bye!");
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
			System.out.println("(1) 전체메뉴  (2) 메뉴추가   (3) 메뉴삭제  (4) 메뉴수정    (0) 종료");
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