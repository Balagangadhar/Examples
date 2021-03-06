package com.bala.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = FileController.BASE_URL)
public class FileController {
	protected final static String BASE_URL = "file";

	/**
	 * URL Template :
	 * http://localhost:8080/spring4-extjs-fileupload-download-template
	 * /action/file/upload/
	 * 
	 * @return Response Object
	 * @throws IOException
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String handleFileUploadConnectionSpecification(
			@RequestParam("file") MultipartFile file) throws IOException {
		final byte[] bytes = file.getBytes();
		System.out.println(bytes);
		String msg = "{\"success\" : true, \"msg\":\"Upload successful\"}";

		return msg;
	}

	@RequestMapping(value = "/download")
	@ResponseBody
	public byte[] handlFileDownload(@RequestParam("p1") String parameter1,
			@RequestParam("p2") String parameter2, HttpServletResponse response) {
		String fileName = "sample.txt";

		try {
			// TODO implement logic to fetch file from server
			File file = new File(fileName);
			PrintStream ps = new PrintStream(new FileOutputStream(file));
			ps.print("This is a sample text file to demonstrate download feature!!!");
			ps.close();
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ fileName + "\"");
			return IOUtils.toByteArray(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String fileNotFoundException = "<h1>No File Exists</h1>";
		return fileNotFoundException.getBytes();
	}
}
