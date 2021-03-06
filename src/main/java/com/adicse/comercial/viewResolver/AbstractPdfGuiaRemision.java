package com.adicse.comercial.viewResolver;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public abstract class AbstractPdfGuiaRemision extends AbstractView {

	
	
	public AbstractPdfGuiaRemision() {
		initView();
	}
	
    private void initView() {

        setContentType("application/pdf");
    }

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
	      ByteArrayOutputStream baos = createTemporaryOutputStream();

	        Document document = new Document(PageSize.A4);
	        PdfWriter writer = PdfWriter.getInstance(document, baos);
	        
	        prepareWriter(model, writer, request);
	        
	        buildPdfMetadata(model, document, request);

	 
			
	        document.open();

	        buildPdfDocument(model, document, writer, request, response);
	        document.close();

	        writeToResponse(response, baos);
		
	}
	
    protected void prepareWriter(Map<String, Object> model, PdfWriter writer, 
            HttpServletRequest request) throws DocumentException {
        writer.setViewerPreferences(getViewerPreferences());
    }

    protected int getViewerPreferences() {
        return PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage;
    }

    protected void buildPdfMetadata(Map<String, Object> model, Document document, 
            HttpServletRequest request) {
    }

    protected abstract void buildPdfDocument(Map<String, Object> model, 
            Document document, PdfWriter writer, HttpServletRequest request, 
            HttpServletResponse response) throws Exception;
	
	

}
