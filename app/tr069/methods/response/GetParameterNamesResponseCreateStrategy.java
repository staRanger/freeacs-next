package tr069.methods.response;

import tr069.Properties;
import tr069.http.HTTPRequestResponseData;
import tr069.xml.Body;
import tr069.xml.Header;
import tr069.xml.Response;
import tr069.xml.TR069TransactionID;

public class GetParameterNamesResponseCreateStrategy implements ResponseCreateStrategy {
    private final Properties properties;

    public GetParameterNamesResponseCreateStrategy(Properties properties) {
        this.properties = properties;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Response getResponse(HTTPRequestResponseData reqRes) {
        if (reqRes.getTR069TransactionID() == null) {
            reqRes.setTR069TransactionID(new TR069TransactionID("FREEACS-" + System.currentTimeMillis()));
        }
        Header header = new Header(reqRes.getTR069TransactionID(), null, null);
        String keyRoot = reqRes.getSessionData().getKeyRoot();
        Body body = new Body() {

            private String parameter = keyRoot;
            private boolean nextLevel0 = properties.isNextLevel0InGPN(reqRes.getSessionData());

            @Override
            public String toXmlImpl() {
                StringBuilder sb = new StringBuilder(3);
                sb.append("\t\t<cwmp:GetParameterNames>\n");
                sb.append("\t\t\t<ParameterPath>");
                sb.append(parameter);
                sb.append("</ParameterPath>\n");
                if (nextLevel0) {
                    sb.append("\t\t\t<NextLevel>0</NextLevel>\n");
                } else {
                    sb.append("\t\t\t<NextLevel>false</NextLevel>\n");
                }
                sb.append("\t\t</cwmp:GetParameterNames>\n");
                return sb.toString();
            }
        };
        return new Response(header, body, reqRes.getSessionData().getCwmpVersionNumber());
    }
}
