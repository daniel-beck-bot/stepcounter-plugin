package tk.stepcounter.diffcount;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import tk.stepcounter.Util;
import tk.stepcounter.diffcount.object.DiffFolderResult;

public class Main {

	private OutputStream output = System.out;

	private File srcdir = null;
	private File olddir = null;
	private String format = null;
	private String encoding = null;

	public void setSrcdir(File srcdir) {
		this.srcdir = srcdir;
	}

	public void setOlddir(File olddir) {
		this.olddir = olddir;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setOutput(OutputStream output) {
		this.output = output;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void executeCount(){
		try {
			if(format == null || format.length() == 0){
				format = "text";
			}
			if(encoding == null || encoding.length() == 0){
				encoding = System.getProperty("file.encoding");
			}

			DiffCounter.setEncoding(encoding);
			DiffFolderResult result = DiffCounter.count(olddir, srcdir);

		} catch(Throwable t){
			t.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		if(args==null || args.length==0){
			System.exit(0);
		}
		String format = null;
		String output = null;
		String encoding = null;
		ArrayList<File> fileList = new ArrayList<File>();
		for(int i=0;i<args.length;i++){
			if(args[i].startsWith("-format=")){
				String[] dim = Util.split(args[i],"=");
				format = dim[1];
			} else if(args[i].startsWith("-output=")){
				String[] dim = Util.split(args[i],"=");
				output = dim[1];
			} else if(args[i].startsWith("-encoding=")){
				String[] dim = Util.split(args[i],"=");
				encoding = dim[1];
			} else {
				fileList.add(new File(args[i]));
			}
		}

		if(fileList.size() == 0){
			System.err.println("Directory which contains current source tree is not specified.");
			System.exit(1);
		}
		if(!fileList.get(0).isDirectory()){
			System.err.println("'" + fileList.get(0).getAbsolutePath() + "' is not directory.");
			System.exit(1);
		}
		if(fileList.size() == 1){
			System.err.println("Directory to compare is not specified.");
			System.exit(1);
		}
		if(!fileList.get(1).isDirectory()){
			System.err.println("'" + fileList.get(1).getAbsolutePath() + "' is not directory.");
			System.exit(1);
		}

		Main main = new Main();
		main.setFormat(format);
		if(output != null && output.length() != 0){
			main.setOutput(new FileOutputStream(output));
		}
		main.setEncoding(encoding);
		main.setSrcdir(fileList.get(0));
		main.setOlddir(fileList.get(1));

		main.executeCount();
	}


}
