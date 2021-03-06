package com.quick.ext.primefaces.base.web;

import java.io.File;
import java.io.InputStream;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;



//import javax.faces.view.ViewScoped;
import org.primefaces.event.FileUploadEvent;

import com.quick.ext.primefaces.base.util.BaseObject;

/**
 *
 * @author Jason
 */
//@ManagedBean
// @ViewScoped
public class BaseFileUpload extends BaseObject {
	private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(BaseMobleMB.class);
	public void upload(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");

		FacesContext.getCurrentInstance().addMessage(null, msg);
		try {
			String savePath = event.getComponent().getAttributes().get("savePath").toString();
			String canReplace = event.getComponent().getAttributes().get("canReplace").toString();
			copyFile(canReplace, savePath, event.getFile().getFileName(), event.getFile().getInputstream());
		} catch (Exception e) {
			logger.error(e);
		}

	}

	private void copyFile(String canReplace, String savePath, String fileName, InputStream in) throws Exception {
		String fullPath = savePath + fileName;
		try {

			File path = new File(savePath);
			if (!path.exists() && path.isDirectory()) {
				path.mkdirs();
			}
			File newFile = new File(fullPath);
			if (newFile.exists()) {
				newFile.createNewFile();
			}
			// todo:jee7
			/*
			 * try (OutputStream out = new FileOutputStream(newFile)) { int read
			 * = 0; byte[] bytes = new byte[1024];
			 * 
			 * while ((read = in.read(bytes)) != -1) { out.write(bytes, 0,
			 * read); }
			 * 
			 * in.close(); out.flush(); }
			 */
			logger.debug("file upload path: " + fullPath + " \n AbsolutePath \n" + newFile.getAbsolutePath());
			System.out.println("New file created!");
		} catch (Exception e) {
			logger.error("fullPath " + fullPath + "\n" + e.getMessage());
			throw new Exception();
		}
	}
}
