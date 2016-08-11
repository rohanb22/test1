package net.example.springbatch.common;

import java.io.Closeable;
import java.io.InputStream;
import java.io.PushbackInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.batch.item.excel.Sheet;
import org.springframework.core.io.Resource;

public class ExcelReader<T> extends AbstractExcelReader<T>{

	private Workbook workbook;

    private InputStream workbookStream;

    @Override
    protected Sheet getSheet(final int sheet) {
        return new PoiSheet(this.workbook.getSheetAt(sheet));
    }

    @Override
    protected int getNumberOfSheets() {
        return this.workbook.getNumberOfSheets();
    }

    @Override
    protected void doClose() throws Exception {
        // As of Apache POI 3.11 there is a close method on the Workbook, prior version
        // lack this method.
        if (workbook instanceof Closeable) {
            this.workbook.close();
        }

        if (workbookStream != null) {
            workbookStream.close();
        }
        this.workbook=null;
        this.workbookStream=null;
    }

    /**
     * Open the underlying file using the {@code WorkbookFactory}. We keep track of the used {@code InputStream} so that
     * it can be closed cleanly on the end of reading the file. This to be able to release the resources used by
     * Apache POI.
     *
     * @param resource the {@code Resource} pointing to the Excel file.
     * @throws Exception is thrown for any errors.
     */
    @Override
    protected void openExcelFile(final Resource resource) throws Exception {
        workbookStream = resource.getInputStream();
        if (!workbookStream.markSupported() && !(workbookStream instanceof PushbackInputStream)) {
            throw new IllegalStateException("InputStream MUST either support mark/reset, or be wrapped as a PushbackInputStream");
        }
        this.workbook = WorkbookFactory.create(workbookStream);
        this.workbook.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);
    }


	
}
