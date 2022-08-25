package il.ac.tau.cs.sw1.bufferedIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class MyBufferReader implements IBufferedReader{

	/*
	 * @pre: bufferSize > 0
	 * @pre: fReader != null
	 */
	int index;
	int size;
	boolean stop;
	FileReader r;
	int cnt;
	ArrayList<String> lst;

	public MyBufferReader(FileReader fReader, int bufferSize){
		this.size = bufferSize;
		this.r = fReader;
		this.stop = false;
		this.index = 0;
		this.cnt = 0;
		this.lst = new ArrayList<>();
	}
	

	@Override
	public void close() throws IOException {
		//Leave this empty
	}


	@Override
	public String getNextLine() throws IOException {
		if (this.lst.size() != 0 && this.lst.size()>this.cnt && this.lst.get(this.cnt) != null) {
			this.cnt +=1;
			return this.lst.get(this.cnt-1);
		}
		if (this.stop) {
			return null;
		}
		int ind = this.cnt;
		int read_index = this.cnt;
		int chars_read = this.size;
		String out = "";
		while (chars_read == this.size && !this.stop) {
			char[] lst = new char[this.size];
			chars_read = this.r.read(lst, 0, this.size);
			if (chars_read < this.size) {
				this.stop = true;
			}
			if (chars_read == -1) {
				this.lst.add(ind,out);
				ind +=1;
				this.stop = true;
			}
			this.index += chars_read;
			for (int i = 0; i < chars_read; i++) {
				if (lst[i] == '\r') {
					continue;
				}
				if (lst[i] == '\n') {
					this.lst.add(ind, out);
					ind +=1;
					out = "";
				} else {
					out += lst[i];
				}
			}

		}
		this.lst.add(out);
		ind += 1;
		this.cnt += 1;
		return this.lst.get(read_index);
	}
	
}
