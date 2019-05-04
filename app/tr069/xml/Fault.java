package tr069.xml;

import java.util.ArrayList;
import java.util.List;

public class Fault {
  private String soapFaultCode;
  private String soapFaultString;
  private String faultCode;
  private String faultString;
  private List<SetParameterValuesFault> parameterFaults = new ArrayList<>();

  public Fault(String soapFaultCode, String soapFaultString, String faultCode, String faultString) {
    this.soapFaultCode = soapFaultCode;
    this.soapFaultString = soapFaultString;
    this.faultCode = faultCode;
    this.faultString = faultString;
    this.parameterFaults = new ArrayList<>();
  }

  public Fault() {
  }

  void addParameterValuesFault(SetParameterValuesFault paramFault) {
    this.parameterFaults.add(paramFault);
  }

  public String toString() {
    StringBuilder str = new StringBuilder();
    if (this.soapFaultCode != null) {
      str.append("SOAP FaultCode       :  ").append(this.soapFaultCode).append("\n");
      str.append("SOAP FaultString     :  ").append(this.soapFaultString).append("\n");
    }
    if (this.faultCode != null) {
      str.append("FaultCode            :  ").append(this.faultCode).append("\n");
      str.append("FaultString          :  ").append(this.faultString).append("\n");
    }
    if (this.parameterFaults != null && !this.parameterFaults.isEmpty()) {
      for (SetParameterValuesFault paramFault : this.parameterFaults) {
        str.append(paramFault).append("\n");
      }
    }
    return str.toString();
  }

  public String getSoapFaultCode() {
    return soapFaultCode;
  }

  public void setSoapFaultCode(String soapFaultCode) {
    this.soapFaultCode = soapFaultCode;
  }

  public String getSoapFaultString() {
    return soapFaultString;
  }

  public void setSoapFaultString(String soapFaultString) {
    this.soapFaultString = soapFaultString;
  }

  public String getFaultCode() {
    return faultCode;
  }

  public void setFaultCode(String faultCode) {
    this.faultCode = faultCode;
  }

  public String getFaultString() {
    return faultString;
  }

  public void setFaultString(String faultString) {
    this.faultString = faultString;
  }

  public List<SetParameterValuesFault> getParameterFaults() {
    return parameterFaults;
  }

  public void setParameterFaults(List<SetParameterValuesFault> parameterFaults) {
    this.parameterFaults = parameterFaults;
  }
}
