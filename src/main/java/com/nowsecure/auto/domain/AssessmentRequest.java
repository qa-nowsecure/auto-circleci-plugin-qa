package com.nowsecure.auto.domain;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * UploadInfo encapsulates meta-data when mobile binary is uploaded
 * 
 * @author sbhatti
 *
 */
public class AssessmentRequest extends MetadataRequest {
    private String group;
    private String account;
    private Long task;
    private String creator;
    private String created;

    public AssessmentRequest() {

    }

    public static AssessmentRequest fromJson(String json) throws ParseException, IOException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(json);
        // for error message
        String name = (String) jsonObject.get("name");
        String message = (String) jsonObject.get("message");
        if (name != null && message != null) {
            throw new IOException(name + " " + message);
        }
        //
        AssessmentRequest request = new AssessmentRequest();
        if (jsonObject.get("application") != null) {
            request.setApplication((String) jsonObject.get("application"));
        }
        request.setGroup((String) jsonObject.get("group"));
        request.setAccount((String) jsonObject.get("account"));
        request.setPlatform((String) jsonObject.get("platform"));
        request.setPackageId((String) jsonObject.get("package"));
        if (jsonObject.get("task") != null) {
            request.setTask(((Number) jsonObject.get("task")).longValue());
        }
        //
        request.setBinary((String) jsonObject.get("binary"));
        request.setCreator((String) jsonObject.get("creator"));
        request.setCreated((String) jsonObject.get("created"));

        if (request.getPackageId() == null || request.getPackageId().isEmpty()) {
            throw new IOException("Preflight failed and package-id could not be found in JSON in \n" + json + "\n");
        }
        if (request.getBinary() == null || request.getBinary().isEmpty()) {
            throw new IOException("Binary not found in JSON in \n" + json + "\n");
        }
        if (request.getTask() == null) {
            throw new IOException("Task not found in JSON in \n" + json + "\n");
        }
        return request;

    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getTask() {
        return task;
    }

    public void setTask(Long task) {
        this.task = task;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "AssessmentRequest [group=" + group + ", account=" + account + ", task=" + task + ", binary=" + getBinary()
               + ", creator=" + creator + ", created=" + created + ", application=" + getApplication() + ", version="
               + getVersion() + ", platform=" + getPlatform() + ", package=" + getPackageId() + "]";
    }

}
