package com.mobioffice.utils.enums;

public enum TestFiles {

    XLS("CellStyle.xlsx"),
    PDF("AttachFilePDF.pdf"),
    SLIDESHOW("Slideshow.pptx"),
    DOCUMENT("Bold Italic Underline.docx"),
    LOCAL_DOC("Overview.docx");

    private final String fileName;


    TestFiles(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}