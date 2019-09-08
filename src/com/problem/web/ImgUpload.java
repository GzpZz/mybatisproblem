package com.problem.web;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.problem.ano.RequestMapping;
import com.problem.ano.ResponseBody;
import com.problem.conf.CommonConfig;
import com.problem.dto.ResultData;
/**
 * 
 *
 */

@WebServlet("/img/*")
@MultipartConfig(maxFileSize = 4*1024*1024 , maxRequestSize = 20*1024*1024 ) 
public class ImgUpload extends DispatcherServlet {
	
	@RequestMapping(value ="upload.action",method="POST")
	@ResponseBody
	public ResultData updaload(HttpServletRequest rq) throws ServletException, IOException {
		Collection<Part> parts = rq.getParts();
		String returnName =  CommonConfig.PRE_FIX;//虚拟目录。
		String saveDir = CommonConfig.BASE_PATH;//真实目录。
		StringBuffer sb = new StringBuffer();
		for(Part  part : parts ) {
			if( part.getContentType()!= null ) { 
				if(part.getSize() > 0) {
					String filename = UUID.randomUUID().toString()+".png" ;
					 part.write(saveDir+filename);
					 sb.append(returnName).append(filename).append("#");
				 }
			} 
		}
		ResultData rd = new ResultData();
		rd.setData(sb.toString());
		return rd;
	}
	 
}
