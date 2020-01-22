package crud;

import java.util.ArrayList;
import java.util.Scanner;

import manager.Factory;
import manager.Managable;
import manager.Manager;

public class CrudManager<E extends Managable & Crudable> extends Manager<E> {
	protected Scanner keyin = null;
	public CrudManager(Scanner keyin) {
		this.keyin = keyin;
	}
	public void addOne(Factory<E> fac) {
		E e = fac.create();
		e.readOnLine(keyin);
		register(e);
	}
	E selectElem() {
		ArrayList<E> arr = new ArrayList<>();
		int indx = 1;
		while (true) {
			arr.clear();
			System.out.print("검색 키워드 : ");
			String kwd = keyin.next();
			keyin.nextLine();
			for (E e : getList()) {
				if (e.compare(kwd)) {
					//e.print();
					arr.add(e);
				}
			}
			indx = 1;
			for (E m : arr) {
				System.out.printf("[%d] ", indx++);
				m.print();
			}
			if (arr.size() == 0)
				continue;
			if (arr.size() == 1)
				indx = 1;
			else {
				System.out.print("번호를 고르세요.. ");
				indx = keyin.nextInt();
				if (indx >= arr.size())
					continue;
			}
			break;
		}
		E e = arr.get(indx-1);
		return e;
	}
	public void removeOne() {
		E e = selectElem();
		remove(e);
	}
	public void remove(E e) {
		System.out.println("정말 삭제하시겠습니까? ");
		String yn = keyin.next();
		if (!yn.equals("y"))
			return;
		boolean check = e.checkRemove(keyin);
		if (check) {
			getList().remove(e);
			System.out.println(e.getName() + "삭제되었습니다.");
		} else {
			
		}
	}
	public void updateOne() {
		E e = selectElem();
		e.update(keyin);
		System.out.println("수정되었습니다.");
		e.print();
	}
}
