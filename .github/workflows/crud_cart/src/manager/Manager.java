package manager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager<E extends Managable> {
	private ArrayList<E> mList = new ArrayList<>();
	public ArrayList<E> getList() {
		return mList;
	}
	public void readAll(Scanner in, Factory<E> fac) {
		E st = null;
		while (true) {
			st = fac.create();
			st.read(in);
			mList.add(st);
		}
	}
	public void register(E e) {
		mList.add(e);
	}
	public void readAll(String filename, Factory<E> fac) {
		Scanner fileIn = Manager.fileOpen(filename);
		E m = null;
		fileIn.nextLine();
		while (fileIn.hasNext()) {
			m = fac.create();
			m.read(fileIn);
			//m.print();
			mList.add(m);
		}
		fileIn.close();
	}
	public void printAll() {
		for (E e : mList) {
			e.print();
		}
	}
	public static Scanner fileOpen(String filename) {
		File f = new File(filename);
		Scanner fileIn = null;
		try {
			fileIn = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return fileIn;
	}
	public E find(int n) {
		for (E m : mList)
			if (m.compare(n)) 
				return m;
		return null;
	}
	public E find(String kwd) {
		for (E m : mList)
			if (m.compare(kwd)) 
				return m;
		return null;
	}
}
