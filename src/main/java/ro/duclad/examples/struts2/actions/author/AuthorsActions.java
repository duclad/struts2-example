package ro.duclad.examples.struts2.actions.author;

import com.opensymphony.xwork2.Action;
import ro.duclad.examples.struts2.actions.ActionResult;
import ro.duclad.examples.struts2.model.Author;
import ro.duclad.examples.struts2.services.AuthorServices;

import java.io.File;
import java.util.Calendar;
import java.util.List;

/**
 * Created by duclad on 8/23/15.
 */
public class AuthorsActions {

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
    private String id;
    private String name;
    private String biography;
    private String miniBio;
    private String imgSrc;
    private String language;
    private Calendar joinedOn;
    private File image;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public Calendar getJoinedOn() {
        return joinedOn;
    }

    public void setJoinedOn(Calendar joinedOn) {
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
        Author author = new Author();
        author.setBiography(biography);
        author.setJoinedOn(joinedOn);
        author.setLanguage(language);
        author.setMiniBio(miniBio);
        author.setName(name);
        try {
            authorServices.create(author);
            result = ActionResult.OK.toString();
        } catch (Exception e) {
            result = ActionResult.ERROR.toString();
            message = e.getMessage();
        }
        return Action.SUCCESS;
    }
}
