package excel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException; 
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Scanner;
import org.json.JSONObject;


@WebServlet("/excel_callback")
public class excel_callback extends HttpServlet {
    public excel_callback() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter write = response.getWriter();
		Scanner json = new Scanner(request.getInputStream()).useDelimiter("\\A");
		String body = json.hasNext() ? json.next() : "";
		JSONObject jsonobj = new JSONObject(body);
		if(jsonobj.getInt("status") == 6) {
			System.out.print(jsonobj.get("url") + "\r\n");
			//文件下载
			URL url = new URL(jsonobj.getString("url"));
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			InputStream inputStream = conn.getInputStream();
			byte[] getData = readInputStream(inputStream);  
			File file = new File("F:\\apache-tomcat-7.0.92\\www\\cache\\" + request.getParameter("id") + ".xlsx");
			OutputStream out = new FileOutputStream(file);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(getData); 
			out.close();
			fos.close();
		    inputStream.close();
		    this.getServletContext().setAttribute(request.getParameter("id"),"1");
		}
		write.write("{\"error\":0}");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public static  byte[] readInputStream(InputStream inputStream) throws IOException {  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        while((len = inputStream.read(buffer)) != -1) {  
            bos.write(buffer, 0, len);  
        }  
        bos.close();  
        return bos.toByteArray();  
    }  
}
