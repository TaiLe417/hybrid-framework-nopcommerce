package commons;

import java.io.File;

public class GlobalConstants {
    public static final String USER_URL = "https://demo.nopcommerce.com/";
    public static final String ADMIN_URL = "https://admin-demo.nopcommerce.com";
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String UPLOAD_FILE = PROJECT_PATH + File.separator + "hybrid-framework-nopcommerce" + File.separator + "uploadFiles" + File.separator;
    public static final String REPORTNG_SCREENSHOT = PROJECT_PATH + File.separator + "ReportNGScreenShots" + File.separator;
    public static final String EXTENT_PATH = PROJECT_PATH + File.separator + "hybrid-framework-nopcommerce" + File.separator + "extentReportV2" + File.separator;
    public static final int LONG_TIMEOUT = 30;
    public static final int SHORT_TIMEOUT = 3;
    public static final String JAVA_VERSION = System.getProperty("java.version");
}
