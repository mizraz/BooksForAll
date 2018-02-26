package example;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Utils {

	public static String getPostBody(HttpServletRequest request) throws IOException {
	    StringBuilder buffer = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        buffer.append(line);
	    }
	    return buffer.toString();
	}
	
	public static void printAllInResultSet(ArrayList<Map<String, Object>> resultList) {
		Iterator<Map<String, Object>> iterator = resultList.iterator();
		while (iterator.hasNext()) {
			Map<String, Object> row = (Map<String, Object>) iterator.next();
			for (Map.Entry<String, Object> column : row.entrySet()) {
				System.out.println(column.getKey() + "/" + column.getValue());
			}
		}
	}

	
	
}
