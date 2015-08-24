package ro.duclad.examples.struts2.actions.author;

import com.opensymphony.xwork2.Action;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import ro.duclad.examples.struts2.model.Author;
import ro.duclad.examples.struts2.services.AuthorServices;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by duclad on 8/24/15.
 */
public class ImageAction {

    private File image;
    private String imageData;
    private String message;
    private Long id;
    private AuthorServices authorServices;
    private String imageFolder;

    public void setAuthorServices(AuthorServices authorServices) {
        this.authorServices = authorServices;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String upload() {
        try {
            File f = this.getImage();
            String imageFileName = ServletActionContext.getRequest().getSession().getId();
            String imageFileExtension = this.getImage().getName().substring(this.getImage().getName().lastIndexOf("."));
            if (this.getImage().getName().endsWith(".exe")) {
                message = "Only images uploads allowed";
                return Action.ERROR;
            }
            FileInputStream inputStream = new FileInputStream(f);
            FileOutputStream outputStream = new FileOutputStream(getImageFolder() + "/" + imageFileName + imageFileExtension);
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, length);
            }
            inputStream.close();
            outputStream.flush();
            imageData = Base64.encodeBase64String(FileUtils.readFileToByteArray(this.getImage()));
            this.setMessage(":8080/ajaxfile/upload/" + this.getImage().getName());
        } catch (Exception e) {
            e.printStackTrace();
            message = "!!!!";
        }
        return Action.SUCCESS;
    }

    public String download() {
        try {
            Author author = authorServices.get(id);
            if (author != null && author.getId() != null) {
                imageData = Base64.encodeBase64String(FileUtils.readFileToByteArray(new File(imageFolder + File.separator + author.getImgSrc())));
                return Action.SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "!!!!";
        }
        return Action.ERROR;
    }

    public String getImageFolder() {
        return imageFolder;
    }

    public void setImageFolder(String imageFolder) {
        this.imageFolder = imageFolder;
    }

    public String getImageData() {
        return imageData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
