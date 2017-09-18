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
	 * ��ȡָ�������ļ�������
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
	 * �ж��ļ��Ƿ����
	 * @param file
	 * @throws NullZeroException
	 */
	public static void FileExist(File file) throws NullZeroException {
		if (file == null) {
			throw new NullZeroException("�ļ�file������!");
		}
	}
	/**
	 * ��ȡstring��reader����
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
	 * ����ָ�������ļ����ƶ�����ֵ
	 * 
	 * @param filepath
	 * @param propertyname
	 * @return ��������ֵ�����û���趨����null
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
	 * ��ȡ���е������ļ��е�����
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
	 * ���ж�ȡ�ļ��е�����
	 * 
	 * @param file
	 *            ��Ҫ��ȡ���ļ�
	 * @return ���ض�ȡ��
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

	// �����ȡ���е��ļ�������
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
	 * ��ȡ·�������е��ļ������ƣ�����·����
	 * 
	 * @param directory
	 *            Ŀ¼����
	 * @return �������еİ������ļ����ƣ�����·����,���������Ҫ���򷵻�false
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
	 *�ƶ�������·�����ļ����Ƶ�ǰ׺������ ��Ӧ���ļ�·��������Ҫ�����ɺ�׺��Ϊxml���ļ�
	 * 
	 * @param basepath
	 *            ������·����basepath = ((ServletContext)
	 *            ActionContext.getContext().get(
	 *            ServletActionContext.SERVLET_CONTEXT)).getRealPath("/");
	 * @param prefix
	 *            �ļ�����ǰ׺
	 * @return ���ɵ��ļ��� ������·������
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
	 * �����ƶ��ı��뽫����д�뵽���Ӧ���ļ���
	 * 
	 * @param content
	 *            ��Ҫд�뵽�ļ��е�����
	 * @param filepath
	 *            �ļ��ĵ�ַ
	 * @param encodingType
	 *            ���뷽ʽ
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
	 * ɾ��ָ��·�����ļ�
	 * 
	 * @param filepath
	 *            �ļ�·��
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
	 * ��ȡ�ĵ����ݺ���
	 * 
	 * @param file
	 *            �ļ�
	 * @return �����ĵ�����
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
	 * ��ȡ�ĵ����ݺ���
	 * 
	 * @param file
	 *            �ļ�
	 * @return �����ĵ�����
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
	 * ��ָ���ı����ȡ�ļ�·��ָ����ļ����ݣ�Ĭ�ϱ��뷽ʽΪUTF-8
	 * 
	 * @param filepath
	 *            �ļ�·��
	 * @param encodingType
	 *            �ļ����뷽ʽ
	 * @return �����ļ�����
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
			// System.out.println("FileOperationUtil.ReadFilecontent���ö�ȡ�ļ�����ʧ��!");
		}
		isr = null;

		return content;
	}

	/**
	 * ��Դ�ļ����ݶ�ȡ��Ŀ¼�ļ��У���Ҫ��ͨ�������Ƶ���ʽ��ȡ�ļ���
	 * 
	 * @param sourfile
	 *            Դ�ļ�
	 * @param targetfile
	 *            Ŀ���ļ�
	 * @return �ɹ�����true ʧ�ܷ���false
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
	 * �ж��ļ��Ƿ����
	 * 
	 * @param filepath
	 *            �ļ�·��
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
	 * ��web·��ת��Ϊ����·�� ���web·��
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
