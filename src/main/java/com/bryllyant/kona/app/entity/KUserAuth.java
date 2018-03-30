package com.bryllyant.kona.app.entity;

import java.util.Date;

import com.bryllyant.kona.data.entity.KEntityObject;

/**
 * Core UserAuth object for framework interaction.
 */
    
public interface KUserAuth extends KEntityObject {
    public Long getId();
    public void setId(Long id);
    
    public Long getUserId();
    public void setUserId(Long userId);
    
    public String getPassword(); 
    public void setPassword(String password);

    public Long getVoiceSampleId();
    public void setVoiceSampleId(Long voiceSampleId);

    public Long getFingerprintId();
    public void setFingerprintId(Long fingerprintId);

    public Long getFacialScanId();
    public void setFacialScanId(Long facialScanId);

    public String getPin(); 
    public void setPin(String pin);

    public String getDuressPin(); 
    public void setDuressPin(String duressPin);

    public String getQuestion1(); 
    public void setQuestion1(String question1);

    public String getAnswer1(); 
    public void setAnswer1(String answer1);

    public String getDuressAnswer1(); 
    public void setDuressAnswer1(String duressAnswer1);


    public String getQuestion2(); 
    public void setQuestion2(String question2);

    public String getAnswer2(); 
    public void setAnswer2(String answer2);

    public String getDuressAnswer2(); 
    public void setDuressAnswer2(String duressAnswer2);
    
    public Date getCreatedDate();
    public void setCreatedDate(Date createdDate);
    
    public Date getUpdatedDate();
    public void setUpdatedDate(Date updatedDate);

}
