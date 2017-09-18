package com.csw.utils.FileUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import com.csw.SystemParams.SystemConfig;
import com.csw.exceptions.NullZeroException;

public class FileOperationUtil {
	
	/**
	 * 读取指定属性文件的属性
	 * 
	 * @param filepath
	 * @param propertyname
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static String readPropertyFile(String filepath, String propertyname) {
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream(filepath));
			return prop.getProperty(propertyname);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 判断文件是否存在
	 * @param file
	 * @throws NullZeroException
	 */
	public static void FileExist(File file) throws NullZeroException {
		if (file == null) {
			throw new NullZeroException("文件file不存在!");
		}
	}
	/**
	 * 获取string的reader对象
	 * @param s
	 * @return
	 */
	public static Reader getStringReader(String s) {
		if (s != null && !s.equals("")) {
			try {
				StringReader stringReader = new StringReader(s);
				return stringReader;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 返回指定属性文件中制定属性值
	 * 
	 * @param filepath
	 * @param propertyname
	 * @return 返回属性值，如果没有设定返回null
	 * @throws Exception
	 */
	public static String getPropertyValue(String filepath, String propertyname)
			throws Exception {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(filepath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop.getProperty(propertyname);
	}
	/**
	 * 获取所有的属性文件中的属性
	 * @param filepath
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> getAllPropertys(String filepath)
			throws IOException {
		Map<String, String> hashmap = new HashMap<String, String>();
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(filepath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		Iterator<Object> propertyiterator = prop.keySet().iterator();
		while (propertyiterator.hasNext()) {
			String propertyname = "" + propertyiterator.next();
			hashmap.put(propertyname, (String) prop.get(propertyname));
		}
		return hashmap;
	}
	/**
	 * 逐行读取文件中的内容
	 * 
	 * @param file
	 *            需要读取的文件
	 * @return 返回读取的
	 */
	public static List<String> ReadOneLineOfFile(File file) {
		String line = "";
		List<String> lines = new ArrayList<String>();
		if (null == file) {
			return null;
		}
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return lines;

	}

	// 保存获取所有的文件的内容
	List<String> filenames = new ArrayList<String>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileOperationUtil fo = new FileOperationUtil();
		List<String> results = fo.GetFilesMethod("d:\\stu");
		System.out.println(results.size());
		for (String stu : results) {
			System.out.println(stu);
		}
	}

	/**
	 * 获取路径上所有的文件的名称（包括路径）
	 * 
	 * @param directory
	 *            目录名称
	 * @return 返回所有的包含的文件名称（包含路径）,如果不符合要求则返回false
	 */
	public List<String> GetFilesMethod(String directory) {
		if (directory == null || directory.length() == 0) {
			return null;
		}
		File file = new File(directory.trim());
		if (file == null) {
			return null;
		}
		if (file.isFile()) {
			filenames.add(file.getAbsolutePath().trim());
		} else {
			for (File f : file.listFiles()) {
				if (f.isDirectory()) {
					GetFilesMethod(f.getAbsolutePath());
				} else {
					filenames.add(f.getAbsolutePath().trim());
				}
			}
		}
		return filenames;
	}

	/**
	 *制定基本的路径和文件名称的前缀，生成 相应的文件路径名，主要是生成后缀名为xml的文件
	 * 
	 * @param basepath
	 *            基本的路径名basepath = ((ServletContext)
	 *            ActionContext.getContext().get(
	 *            ServletActionContext.SERVLET_CONTEXT)).getRealPath("/");
	 * @param prefix
	 *            文件名的前缀
	 * @return 生成的文件名 （含有路径）；
	 */
	public static String CreateFilePathOperation(String basepath, String prefix) {
		if (basepath.endsWith("/") || basepath.endsWith("\\\\")) {
		} else {
			basepath += "\\";
		}
		Calendar calendar = new GregorianCalendar();
		int year = calendar.get(Calendar.YEAR);
		int mounth = calendar.get(Calendar.MONTH + 1);
		int day = calendar.get(Calendar.DATE);
		int minute = calendar.get(Calendar.MINUTE);
		int house = calendar.get(Calendar.HOUR);
		int second = calendar.get(Calendar.SECOND);
		int millsecond = calendar.get(Calendar.MILLISECOND);
		Random radom = new Random();
		String result = prefix + "" + year + mounth + day + house + minute
				+ second + millsecond + radom.nextInt(100);
		String direc = basepath + "transformxsls\\middleresults";
		if (!new File(direc).exists()) {
			new File(direc).mkdir();
		}
		String returntext = basepath + "transformxsls\\middleresults\\"
				+ result + ".xml";
		try {
			new File(returntext).createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returntext;
	}

	/**
	 * 根据制定的编码将内容写入到相对应的文件中
	 * 
	 * @param content
	 *            需要写入到文件中的内容
	 * @param filepath
	 *            文件的地址
	 * @param encodingType
	 *            编码方式
	 */
	public static void WriteToFileContent(String content, String filepath,
			String encodingType) {
		File file = new File(filepath);
		try {
			file.createNewFile();
			OutputStreamWriter osw = new OutputStreamWriter(
					new FileOutputStream(file), encodingType);
			BufferedWriter bwriter = new BufferedWriter(osw);
			bwriter.write(content);
			bwriter.flush();
			bwriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除指定路径的文件
	 * 
	 * @param filepath
	 *            文件路径
	 */
	public static void DeleteFile(String filepath) {
		if (filepath != null) {
			File file = new File(filepath);
			if (file.exists()) {
				file.delete();
			}
		}
	}

	/**
	 * 读取文档内容函数
	 * 
	 * @param file
	 *            文件
	 * @return 返回文档内容
	 */
	public static String ReadFileContentByFile(File file) {
		String content = "";
		try {
			InputStreamReader osw = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			BufferedReader br = new BufferedReader(osw);
			String tempcontent = "";
			while ((tempcontent = br.readLine()) != null) {
				content += tempcontent;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	/**
	 * 读取文档内容函数
	 * 
	 * @param file
	 *            文件
	 * @return 返回文档内容
	 */
	public static String ReadFileContentByFile(File file, String stringcode) {
		String content = "";
		try {
			InputStreamReader osw = new InputStreamReader(new FileInputStream(
					file), stringcode);
			BufferedReader br = new BufferedReader(osw);
			String tempcontent = "";
			while ((tempcontent = br.readLine()) != null) {
				content += tempcontent;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	/**
	 * 以指定的编码读取文件路径指向的文件内容，默认编码方式为UTF-8
	 * 
	 * @param filepath
	 *            文件路径
	 * @param encodingType
	 *            文件编码方式
	 * @return 返回文件内容
	 */
	public static String ReadFileContent(String filepath, String encodingType) {
		String content = "";
		InputStreamReader isr = null;
		if (encodingType == null) {
			encodingType = "UTF-8";
		}
		try {
			isr = new InputStreamReader(
					new FileInputStream(new File(filepath)), encodingType);
			BufferedReader br = new BufferedReader(isr);
			String tempcontent = "";
			while ((tempcontent = br.readLine()) != null) {
				content += tempcontent;
			}
			isr.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("FileOperationUtil.ReadFilecontent调用读取文件内容失败!");
		}
		isr = null;

		return content;
	}

	/**
	 * 将源文件内容读取到目录文件中（主要是通过二进制的形式读取文件）
	 * 
	 * @param sourfile
	 *            源文件
	 * @param targetfile
	 *            目标文件
	 * @return 成功返回true 失败返回false
	 */
	public static boolean TransmitFileContentToOtherByBinary(File sourfile,
			File targetfile) {
		boolean bol = true;
		FileInputStream in = null;
		FileOutputStream out = null;
		byte[] buf = new byte[1024];
		try {
			int len = 0;
			in = new FileInputStream(sourfile);
			out = new FileOutputStream(targetfile);
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			bol = false;
		}
		return bol;
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param filepath
	 *            文件路径
	 * @return
	 */
	public static boolean IsExistOfFile(String filepath) {
		try {
			return new File(filepath).exists();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 将web路径转换为本地路径 针对web路径
	 * 
	 * @param webfilepath
	 * @return
	 */
	public static String TranslateWebFilepathToLocalFilePath(String webfilepath) {
		try {
			return SystemConfig.sensorsavebasepath
					+ webfilepath.substring(SystemConfig.sensorimagesaveurl
							.length());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
