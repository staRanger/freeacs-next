package tr069.methods.response;

import tr069.Properties;
import tr069.SessionData;
import tr069.http.HTTPRequestResponseData;
import tr069.xml.Body;
import tr069.xml.Header;
import tr069.xml.Response;
import tr069.xml.TR069TransactionID;

public class DownloadResponseCreateStrategy implements ResponseCreateStrategy {
    private static final String DELAY_SECONDS = "0";

    private final Properties properties;

    public DownloadResponseCreateStrategy(Properties properties) {
        this.properties = properties;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Response getResponse(HTTPRequestResponseData reqRes) {
        if (reqRes.getTR069TransactionID() == null) {
            reqRes.setTR069TransactionID(new TR069TransactionID("FREEACS-" + System.currentTimeMillis()));
        }
        Header header = new Header(reqRes.getTR069TransactionID(), null, null);
        SessionData sessionData = reqRes.getSessionData();
        SessionData.Download download = sessionData.getDownload();
        dbi.util.ProvisioningMessage pm = sessionData.getProvisioningMessage();
        final String downloadType;
        String tn = download.getFile().getTargetName();
        String commandKey = download.getFile().getVersion();
        if (download.getFile().getType() == dbi.FileType.SOFTWARE) {
            downloadType = "1 Firmware Upgrade Image";
            pm.setProvOutput(dbi.util.ProvisioningMessage.ProvOutput.SOFTWARE);
        } else if (download.getFile().getType() == dbi.FileType.TR069_SCRIPT) {
            downloadType = "3 Vendor Configuration File";
            pm.setProvOutput(dbi.util.ProvisioningMessage.ProvOutput.SCRIPT);
        } else {
            downloadType = null;
        }
        String version = download.getFile().getVersion();
        pm.setFileVersion(version);
        String username = sessionData.getUnitId();
        String password = sessionData.getAcsParameters().getValue(dbi.util.SystemParameters.SECRET);
        Body body = new Body() {
            private boolean fileAuthUsed = properties.isFileAuthUsed();
            private String url = download.getUrl();
            private String type = downloadType;
            private int filesize = download.getFile().getLength();
            private String targetFilename = tn;

            @Override
            public String toXmlImpl() {
                StringBuilder sb = new StringBuilder(3);
                sb.append("\t\t<cwmp:Download xmlns:cwmp=\"urn:dslforum-org:cwmp-").append(reqRes.getSessionData().getCwmpVersionNumber()).append("\">\n");
                sb.append("\t\t\t<CommandKey>");
                if (commandKey != null) {
                    sb.append(commandKey);
                } else {
                    sb.append("Download_To_CPE");
                }
                sb.append("</CommandKey>\n");
                sb.append("\t\t\t<FileType>");
                sb.append(type);
                sb.append("</FileType>\n");
                sb.append("\t\t\t<URL>");
                sb.append(url);
                sb.append("</URL>\n");

                sb.append("\t\t\t<Username>");
                if (fileAuthUsed) {
                    sb.append(username);
                }
                sb.append("</Username>\n");
                sb.append("\t\t\t<Password>");
                if (fileAuthUsed) {
                    sb.append(password);
                }
                sb.append("</Password>\n");
                sb.append("\t\t\t<FileSize>").append(filesize).append("</FileSize>\n");
                if (targetFilename != null) {
                    sb.append("\t\t\t<TargetFileName>").append(targetFilename).append("</TargetFileName>\n");
                } else {
                    sb.append("\t\t\t<TargetFileName></TargetFileName>\n");
                }
                sb.append("\t\t\t<DelaySeconds>");
                sb.append(DELAY_SECONDS);
                sb.append("</DelaySeconds>\n");
                sb.append("\t\t\t<SuccessURL></SuccessURL>\n");
                sb.append("\t\t\t<FailureURL></FailureURL>\n");
                sb.append("\t\t</cwmp:Download>\n");
                return sb.toString();
            }
        };
        return new Response(header, body, reqRes.getSessionData().getCwmpVersionNumber());
    }
}