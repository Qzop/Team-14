package Account;

import java.sql.Date;
import java.sql.Time;

public class Appointment extends CreateAccount {
    private String medication;
    private String summary;
    private String pharmacy;
    private String immunizations;
    private String prevHealthIssues;
    private Date date;
    private Time time;


    // Appointment constructor.
    public void appointment(String medicationString, String summaryString, String pharamcyString, String immunizationsString, String prevHealthIssuesStr, Date date, Time time)
    {
        this.medication = medicationString;
        this.summary = summaryString;
        this.pharmacy = pharamcyString;
        this.immunizations = immunizationsString;
        this.prevHealthIssues = prevHealthIssuesStr;
        this.date = date;
        this.time = time;
    }
    /**
     * GETTERS
     *    &
     * SETTERS
     */
    /**
     * @return String return the medication
     */
    public String getMedication() {
        return medication;
    }

    /**
     * @param medication the medication to set
     */
    public void setMedication(String medication) {
        this.medication = medication;
    }

    /**
     * @param summary the summary to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return String return the medication
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @return String return the pharmacy
     */
    public String getPharmacy() {
        return pharmacy;
    }

    /**
     * @param pharmacy the pharmacy to set
     */
    public void setPharmacy(String pharmacy) {
        this.pharmacy = pharmacy;
    }

    /**
     * @return String return the immunizations
     */
    public String getImmunizations() {
        return immunizations;
    }

    /**
     * @param immunizations the immunizations to set
     */
    public void setImmunizations(String immunizations) {
        this.immunizations = immunizations;
    }

    /**
     * @return String return the prevHealthIssues
     */
    public String getPrevHealthIssues() {
        return prevHealthIssues;
    }

    /**
     * @param prevHealthIssues the prevHealthIssues to set
     */
    public void setPrevHealthIssues(String prevHealthIssues) {
        this.prevHealthIssues = prevHealthIssues;
    }

    /**
     * @return Date return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return Time return the time
     */
    public Time getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Time time) {
        this.time = time;
    }

}
