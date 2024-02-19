package step.learning.services.form_parse;

import org.apache.commons.fileupload.FileItem;

import java.util.Map;

public interface FormParseResult {
    Map<String, String> getFields() ;
    Map<String, FileItem> getFiles() ;
}
