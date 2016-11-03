package Books.HeadFirst.Decorator_3.io;

import java.io.*;

/**
 * Программа преобразует все символы верхнего регистра во входном потоке к нижнему регистру.
 */

public class InputTest {
	public static void main(String[] args) throws IOException {
		int c;

		try {
			InputStream in = 
				new LowerCaseInputStream(
					new BufferedInputStream(
						new FileInputStream("test.txt")));

			while((c = in.read()) >= 0) {
				System.out.print((char)c);
			}

			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
