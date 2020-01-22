package oldstore;

import java.util.HashSet;
import java.util.Scanner;

import crud.Crudable;
import manager.Managable;

public class Item implements Managable, Crudable {
	public String itemId;
	String name;
	int price;

	@Override
	public void read(Scanner sc) {
		itemId = sc.next();
		name = sc.next();
		price = sc.nextInt();
	}
	@Override
	public void print() {
		System.out.printf("[%s] %s\t%5d원%n", itemId, name, price);
	}
	public int getPrice() {
		return price;
	}
	public boolean compare(String kwd) {
		if (kwd.length() == 0) return false;
		return (kwd.equals(itemId) || name.contains(kwd));
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void readOnLine(Scanner keyin) {
		// TODO Auto-generated method stub
		String tmp = null;
		while (true) {
			System.out.print("  >> 메뉴 아이디 : ");
			tmp = keyin.next();
			Item it = SmallStore.findItem(tmp);
			if (it != null) {
				System.out.println("이미 있는 아이디입니다.");
				continue;
			}
			break;
		}
		itemId = tmp;
		System.out.print("  >> 메뉴명 : ");
		name = keyin.next();
		System.out.print("  >> 가격 : ");
		price = keyin.nextInt();
		System.out.print("  신규추가 : ");
		print();
	}
	@Override
	public void update(Scanner keyin) {
		// TODO Auto-generated method stub
		String tmp = null;
		System.out.print("  >> 메뉴명(또는 엔터) : ");
		tmp = keyin.nextLine().trim();
		if (tmp.length() > 0) name = tmp;
		System.out.print("  >> 가격 (또는 엔터) : ");
		tmp = keyin.nextLine().trim();
		if (tmp.length() > 0) price = Integer.parseInt(tmp);
		System.out.print("  신규추가 : ");
	}
	@Override
	public boolean compare(int n) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean checkRemove(Scanner keyin) {
		// TODO Auto-generated method stub
		return false;
	}
}