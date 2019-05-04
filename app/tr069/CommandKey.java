package tr069;

import tr069.http.HTTPRequestResponseData;

public class CommandKey {
  private String cpeKey;
  private String serverKey;

  public void setServerKey(HTTPRequestResponseData reqRes) {
    this.serverKey =
        reqRes.getSessionData().getUnit().getParameterValue(dbi.util.SystemParameters.JOB_CURRENT_KEY);
  }

  public boolean isEqual() {
    return serverKey == null
        || "".equals(serverKey.trim())
        || (cpeKey != null && cpeKey.equals(serverKey));
  }

  public String getCpeKey() {
    return cpeKey;
  }

  public void setCpeKey(String cpeKey) {
    this.cpeKey = cpeKey;
  }

  public String getServerKey() {
    return serverKey;
  }

  public void setServerKey(String serverKey) {
    this.serverKey = serverKey;
  }
}
