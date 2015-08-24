package ro.duclad.examples.struts2.actions.author;

import com.opensymphony.xwork2.Action;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import ro.duclad.examples.struts2.actions.ActionResult;
import ro.duclad.examples.struts2.model.Author;
import ro.duclad.examples.struts2.services.AuthorServices;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

/**
 * Created by duclad on 8/23/15.
 */
public class CrudActions {

    private AuthorServices authorServices;
    // Holds Start Page Index
    private int jtStartIndex;
    // Hold records to be displayed per Page
    private int jtPageSize;
    // Hold total number of records
    private long totalRecordCount;

    private List<Author> records;
    private Author record;
    private String result;
    private String message;
    private Long id;
    private String name;
    private String biography;
    private String miniBio;
    private String imgSrc;
    private String language;
    private String joinedOn;
    private String imageFolder;


    public Author getRecord() {
        return record;
    }

    public void setRecord(Author record) {
        this.record = record;
    }

    public AuthorServices getAuthorServices() {
        return authorServices;
    }

    public void setAuthorServices(AuthorServices authorServices) {
        this.authorServices = authorServices;
    }

    public List<Author> getRecords() {
        return records;
    }

    public void setRecords(List<Author> records) {
        this.records = records;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getJoinedOn() {
        return joinedOn;
    }

    public void setJoinedOn(String joinedOn) {
        this.joinedOn = joinedOn;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMiniBio() {
        return miniBio;
    }

    public void setMiniBio(String miniBio) {
        this.miniBio = miniBio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getJtPageSize() {
        return jtPageSize;
    }

    public void setJtPageSize(int jtPageSize) {
        this.jtPageSize = jtPageSize;
    }

    public int getJtStartIndex() {
        return jtStartIndex;
    }

    public void setJtStartIndex(int jtStartIndex) {
        this.jtStartIndex = jtStartIndex;
    }

    public long getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setTotalRecordCount(long totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public String list() {
        try {
            records = authorServices.getAll();
            result = ActionResult.OK.toString();
        } catch (Exception e) {
            result = ActionResult.ERROR.toString();
            message = e.getMessage();
        }
        return Action.SUCCESS;
    }

    public String page() {
        try {
            records = authorServices.getAll(jtStartIndex, jtPageSize);
            result = ActionResult.OK.toString();
        } catch (Exception e) {
            result = ActionResult.ERROR.toString();
            message = e.getMessage();
        }
        return Action.SUCCESS;
    }

    public String create() {
        record = new Author();
        record.setBiography(biography);
        record.setJoinedOn(DateTime.parse(joinedOn, DateTimeFormat.forPattern("yyyy-MM-dd")).toDate());
        record.setLanguage(language);
        record.setMiniBio(miniBio);
        record.setName(name);
        try {
            String newFileName = commitFileUpload();
            record.setImgSrc(newFileName);
            if (StringUtils.isBlank(record.getImgSrc())) {
                result = ActionResult.ERROR.toString();
                message = "An image is required";
            } else {
                authorServices.create(record);
                result = ActionResult.OK.toString();
            }
        } catch (Exception e) {
            result = ActionResult.ERROR.toString();
            message = e.getMessage();
        }
        return Action.SUCCESS;
    }

    public String update() {
        record = authorServices.get(id);
        record.setBiography(biography);
        record.setJoinedOn(DateTime.parse(joinedOn, DateTimeFormat.forPattern("yyyy-MM-dd")).toDate());
        record.setLanguage(language);
        record.setMiniBio(miniBio);
        record.setName(name);
        try {
            String newFileName = commitFileUpload();
            if (StringUtils.isNotBlank(newFileName)) {
                FileUtils.forceDelete(new File(imageFolder+File.separator+record.getImgSrc()));
                record.setImgSrc(newFileName);
            }
            if (StringUtils.isBlank(record.getImgSrc())) {
                result = ActionResult.ERROR.toString();
                message = "An image is required";
            } else {
                authorServices.update(record);
                result = ActionResult.OK.toString();
            }
        } catch (Exception e) {
            result = ActionResult.ERROR.toString();
            message = e.getMessage();
        }
        return Action.SUCCESS;
    }

    private String commitFileUpload() throws IOException {
        String tmpFileName = ServletActionContext.getRequest().getSession().getId();
        List files = (List) FileUtils.listFiles(new File(imageFolder), new WildcardFileFilter("*" + tmpFileName + "*"), TrueFileFilter.TRUE);
        String newFileName = "";
        if (files.size() > 0) {
            String uniqueName = new BigInteger(128, new SecureRandom()).toString(32);
            File tmpImage = (File) files.get(0);
            newFileName = StringUtils.replace(tmpImage.getName(), tmpFileName, uniqueName);
            FileUtils.moveFile(tmpImage, new File(imageFolder + File.separator + newFileName));
        }
        return newFileName;
    }

    public String delete() {
        try {
            if (id != null) {
                authorServices.delete(id);
                result = ActionResult.OK.toString();
            }
        } catch (Exception e) {
            result = ActionResult.ERROR.toString();
            message = e.getMessage();
        }
        return Action.SUCCESS;
    }

    public void setImageFolder(String imageFolder) {
        this.imageFolder = imageFolder;
    }
}
