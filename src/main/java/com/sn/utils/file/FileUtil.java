package com.sn.utils.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.sn.utils.crypto.Base64;

/**
 * 文件工具
 */
public class FileUtil {

	/**
	 * 将文件夹复制到新文件夹中，合并文件
	 * 
	 * @param oldPath
	 * @param newPath
	 * @throws Exception
	 */
	public static void copyFile(String oldPath, String newPath)
			throws Exception {
		ByteBuffer bb = ByteBuffer.allocate(100000);
		File oldf = new File(oldPath);
		if (!oldf.exists())
			throw new Exception();
		File newf = new File(newPath);
		if (!newf.exists()) {
			newf.mkdirs();
		}
		File temp = null;
		String[] list = oldf.list();
		for (int i = 0; i < list.length; i++) {
			if (oldPath.endsWith(File.separator)) {
				temp = new File(oldPath + list[i]);
			} else {
				temp = new File(oldPath + File.separator + list[i]);
			}
			if (temp.isFile()) {
				FileInputStream fis = new FileInputStream(temp);
				FileChannel in = fis.getChannel();
				FileOutputStream fos = new FileOutputStream(newf + "/"
						+ temp.getName().toString());
				FileChannel out = fos.getChannel();
				while (in.read(bb) != -1) {
					bb.flip();
					out.write(bb);
					bb.clear();
				}
				out.close();
				fos.close();
				in.close();
				fis.close();
			}
			if (temp.isDirectory()) {
				copyFile(oldPath + "/" + list[i], newPath + "/" + list[i]);
			}
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param dir
	 * @return
	 * @throws Exception
	 */
	public static void delFile(String dir) throws Exception {
		try {
			File file = new File(dir);
			if (file.isDirectory()) {
				String[] list = file.list();
				for (int i = 0; i < list.length; i++) {
					delFile(dir + "/" + list[i]);
				}
			}
			if (file.exists())
				file.delete();
		} catch (Exception e) {

		}
	}

	/**
	 * file 转byte NIO way
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray(File file) throws IOException {
		FileChannel channel = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(file);
			channel = fs.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
				// do nothing
			}
			return byteBuffer.array();
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
			}
			try {
				fs.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * @Title: base64ToFile
	 * @Description: base64转成文件
	 * @author yangzhou
	 * @date 2015-6-15 下午8:34:26
	 * @param base64
	 *            base64字符串
	 * @param fileName
	 *            扩展名的文件名
	 * @param path
	 *            文件存放路径
	 * @return boolean 返回 true成功，false失败
	 */
	public static boolean base64ToFile(String base64, String fileName,
			String path) {
		byte[] bytes = Base64.decode(base64);
		FileOutputStream fos = null;
		FileChannel out = null;
		try {
			fos = new FileOutputStream(path + "/" + fileName);
			out = fos.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
			out.write(byteBuffer);
			byteBuffer.clear();
			out.close();
			fos.close();
			return true;
		} catch (Exception e) {
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (Exception e2) {

			}
		}
		return false;
	}

	/**
	 * 把字节数组保存为一个文件
	 * 
	 * @param b
	 * @param outputFile
	 * @return
	 */
	public static File getFileFromBytes(byte[] b, String outputFile) {
		BufferedOutputStream stream = null;
		File file = null;
		try {
			file = new File(outputFile);
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return file;
	}

	/**
	 * 创建文件夹
	 * 
	 * @param path
	 */
	public void createDir(String path) {
		File dir = new File(path);
		if (!dir.exists())
			dir.mkdir();
	}

	/**
	 * 创建新文件
	 * 
	 * @param path
	 * @param filename
	 * @throws Exception
	 */
	public void createFile(String path, String filename) throws Exception {
		File file = new File(path + "/" + filename);
		if (!file.exists())
			file.createNewFile();
	}

	/**
	 * 创建html
	 * 
	 * @param path
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static File createHtml(String path, String content) throws Exception {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		} else {
			file.createNewFile();
		}
		FileOutputStream out = new FileOutputStream(file, true);
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
		sb.append("</head>");
		sb.append("<body>");
		sb.append(content);
		sb.append("</body>");
		sb.append("</html>");
		out.write(sb.toString().getBytes("utf-8"));
		out.close();
		return file;
	}

	/**
	 * 删除目录
	 * 
	 * @param path
	 */
	public void delDir(String path) {
		File dir = new File(path);
		if (dir.exists()) {
			File[] tmp = dir.listFiles();
			for (int i = 0; i < tmp.length; i++) {
				if (tmp[i].isDirectory()) {
					delDir(path + "/" + tmp[i].getName());
				} else {
					tmp[i].delete();
				}
			}
			dir.delete();
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param path
	 * @param filename
	 */
	public void delFile(String path, String filename) {
		File file = new File(path + "/" + filename);
		if (file.exists() && file.isFile())
			file.delete();
	}
}
