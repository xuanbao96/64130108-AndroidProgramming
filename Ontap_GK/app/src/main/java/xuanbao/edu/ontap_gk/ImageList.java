package xuanbao.edu.ontap_gk;

public class ImageList {
    String GKImageFileName;
    String GKCaption;

    public ImageList(String GKImageFileName, String GKCaption) {
        this.GKImageFileName = GKImageFileName;
        this.GKCaption = GKCaption;
    }

    public String getGKImageFileName() {
        return GKImageFileName;
    }

    public void setGKImageFileName(String GKImageFileName) {
        this.GKImageFileName = GKImageFileName;
    }

    public String getGKCaption() {
        return GKCaption;
    }

    public void setGKCaption(String GKCaption) {
        this.GKCaption = GKCaption;
    }
}
