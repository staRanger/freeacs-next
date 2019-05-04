package tr069.xml;

public class Header {
  private TR069TransactionID id;
  private HoldRequests holdRequests;
  private NoMoreRequests noMoreRequests;

  public Header() {
  }

  public Header(TR069TransactionID id, HoldRequests holdRequests, NoMoreRequests noMoreRequests) {
    this.id = id;
    this.holdRequests = holdRequests;
    this.noMoreRequests = noMoreRequests;
  }

  public Header getHeader() {
    return this;
  }

  void setHeaderField(String key, String value) {
    if ("ID".equals(key)) {
      if (id != null) {
        id.setId(value);
      } else {
        this.id = new TR069TransactionID(value);
      }
    } else if ("NoMoreRequests".equals(key)) {
      if (noMoreRequests != null) {
        noMoreRequests.setNoMoreRequestsFlag(value);
      } else {
        this.noMoreRequests = new NoMoreRequests(value);
      }
    } else if ("HoldRequests".equals(key)) {
      if (holdRequests != null) {
        holdRequests.setHoldRequestsFlag(value);
      } else {
        this.holdRequests = new HoldRequests(value);
      }
    }
  }

  String toXml() {
    StringBuilder sb = new StringBuilder(6);
    if (id != null || holdRequests != null) {
      sb.append("<").append("soapenv").append(":Header>\n");
      if (id != null) {
        sb.append(id.toXml());
      }
      if (holdRequests != null) {
        sb.append(holdRequests.toXml());
      }
      if (noMoreRequests != null) {
        sb.append(noMoreRequests.toXml());
      }
      sb.append("</").append("soapenv").append(":Header>\n");
    } else {
      sb.append("<").append("soapenv").append(":Header/>\n");
    }
    return sb.toString();
  }

  public TR069TransactionID getId() {
    return id;
  }

  public void setId(TR069TransactionID id) {
    this.id = id;
  }

  public HoldRequests getHoldRequests() {
    return holdRequests;
  }

  public void setHoldRequests(HoldRequests holdRequests) {
    this.holdRequests = holdRequests;
  }

  public NoMoreRequests getNoMoreRequests() {
    return noMoreRequests;
  }

  public void setNoMoreRequests(NoMoreRequests noMoreRequests) {
    this.noMoreRequests = noMoreRequests;
  }
}