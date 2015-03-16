package shared;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import shared.comm.ServerException;

public class Util {
    private final static Logger LOG = Logger.getLogger(Util.class.getName());
	private Util() {}
	
	/**
	 * Return just the name of the file regardless of operating system
	 * @param filePath Path of file (relative or absolute)
	 * @return Name of file without directory names
	 */
	public static String getFileName(String filePath) {
		int lastForwardSlash = filePath.lastIndexOf('/');
		int lastBackSlash = filePath.lastIndexOf('\\');
		int lastSlash = Math.max(lastForwardSlash, lastBackSlash);
		if (lastSlash == -1) {
			return filePath;
		}
		else {
			return filePath.substring(lastSlash + 1);
		}
	}
	
	/**
	 * Read and return the next line of the input stream passed in.
	 * @param input The InputStream to read from
	 * @return The next line of text from the specified InputStream
	 * @throws IOException
	 */
	public static String readLine(InputStream input) throws IOException {
		StringBuilder result = new StringBuilder();
		
		int b;
		while ((b = input.read()) >= 0) {
			char c = (char)b;
			if (c == '\r') {
				continue;
			}
			else if (c == '\n') {
				break;
			}
			else {
				result.append(c);
			}
		}
		
		return result.toString();
	}
	
	/**
	 * Safely closes the specified Closeable object, checking for null and
	 * catching expections
	 * 
	 * @param obj The Closeable object to be safely closed
	 */
	public static void safeClose( Closeable... closeables ) {
		for (Closeable closeable : closeables) {
			if (closeable != null) {
				try {
					closeable.close();
				} catch( Exception ex ) {
					LOG.warning("Error while closing resource: " + ex.getMessage());
				}
			}
		}
	}
	
	/**
	 * Safely closes the specified Closeable object, checking for null and
	 * catching expections
	 * 
	 * @param obj The Closeable object to be safely closed
	 */
	public static void safeClose( AutoCloseable... closeables ) {
		for (AutoCloseable closeable : closeables) {
			if (closeable != null) {
				try {
					closeable.close();
				} catch( Exception ex ) {
					LOG.warning("Error while closing resource: " + ex.getMessage());
				}
			}
		}
	}
	
    /**
     * Searches recursively for a file matching the given regex and returns the first match
     * @param folder
     * @param regex
     * @return
     * @throws FileNotFoundException
     */
	public static File findFile(File folder, String regex) throws FileNotFoundException {
		if (folder.isDirectory()) {
			File foundFile = _findFile(folder,regex);
			if (null != foundFile) {
				return foundFile;
			} else {
				throw new FileNotFoundException("No files in directory match the given regex: " + regex);
			}
		} else {
			if (folder.getName().matches(regex)) {
				return folder;
			} else {
				throw new FileNotFoundException("The given file does not match the regex");
			}
		}
	}
	
	/*
	 * This is a recursive function for findFile()
	 */
	private static File _findFile(File folder, String regex) {
		for (File subFile : folder.listFiles()) {
			if (subFile.getName().matches(regex)) {
				return subFile;
			}
		}
		for (File subFile : folder.listFiles()) {
			if (subFile.isDirectory()) {
				File foundFile = _findFile(subFile, regex);
				if (null != foundFile) {
					return foundFile;
				}
			}
		}
		return null;
	}
	
	/**
	 * Read a file into a string
	 * @param path the file to read into a string
	 * @return a string representing the file
	 * @throws IOException
	 */
	public static String readFile(String path) 
			throws IOException {
		return readFile(path, Charset.defaultCharset());
	}
	
	/**
	 * Read a file into a string
	 * @param path the file to read into a string
	 * @param encoding the encoding of the file
	 * @return a string representing the file
	 * @throws IOException
	 */
	public static String readFile(String path, Charset encoding) 
			throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return encoding.decode(ByteBuffer.wrap(encoded)).toString();
	}
	
	/**
	 * Converts a string object to a HTMLDocument
	 * @param document the string representation of the document
	 * @return an HTMLDocument of the string passed in
	 * @throws IOException
	 * @throws BadLocationException
	 */
	public static HTMLDocument toHTMLDocument(String document) throws IOException, BadLocationException {
		Reader stringReader = new StringReader(document);
		HTMLEditorKit htmlKit = new HTMLEditorKit();
		HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
		htmlKit.read(stringReader, htmlDoc, 0);
		return htmlDoc;
	}
	
	/**
	 * Reads the text from the URL and returns it as a string
	 * @param url the URL of the file to retrieve
	 * @return a string representation of the file from the given URL
	 * @throws IOException 
	 * @throws Exception
	 */
	public static String readText(String url) throws IOException {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                    connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) 
            response.append(inputLine);

        in.close();

        return response.toString();
    }
	
	/**
	 * Join a collection of strings and add given separator. This is just like the 
	 * join in python (couldn't live without it)
	 * @param separator What to separate the given strings with
	 * @param words the words to tack together with the given separator
	 * @return
	 */
	public static String join(String separator, String... words) {
		if (words.length == 1) {
			return words[0];
		}
	    StringBuilder wordList = new StringBuilder();
	    for (String word : words) {
	        wordList.append(word + separator);
	    }
	    return new String(wordList.delete(wordList.length() - separator.length(), wordList.length()));
	}
	
	public static String getURL(String file) throws ServerException {
		if (file == null || file.equals("null")) {
			return null;
		}
		try
		{
		    InetAddress addr = InetAddress.getLocalHost();
		    String port = System.getenv("server.port");
		    return "http://" + addr.getHostName() + ":" + port + "/" + file;
		}
		catch (UnknownHostException ex)
		{
		    throw new ServerException("Hostname can not be resolved");
		}
	}
	
	/**
	 * A dirty environment setter scraped from the bowels of the internet.
	 * @param newenv the new environment to set
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void setEnv(Map<String, String> newenv)
	{
	  try
	    {
	        Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
	        Field theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment");
	        theEnvironmentField.setAccessible(true);
	        Map<String, String> env = (Map<String, String>) theEnvironmentField.get(null);
	        env.putAll(newenv);
	        Field theCaseInsensitiveEnvironmentField = processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
	        theCaseInsensitiveEnvironmentField.setAccessible(true);
	        Map<String, String> cienv = (Map<String, String>) theCaseInsensitiveEnvironmentField.get(null);
	        cienv.putAll(newenv);
	    }
	    catch (NoSuchFieldException e)
	    {
	      try {
	        Class[] classes = Collections.class.getDeclaredClasses();
	        Map<String, String> env = System.getenv();
	        for(Class cl : classes) {
	            if("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
	                Field field = cl.getDeclaredField("m");
	                field.setAccessible(true);
	                Object obj = field.get(env);
	                Map<String, String> map = (Map<String, String>) obj;
	                map.clear();
	                map.putAll(newenv);
	            }
	        }
	      } catch (Exception e2) {
	        e2.printStackTrace();
	      }
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    } 
	}
}
