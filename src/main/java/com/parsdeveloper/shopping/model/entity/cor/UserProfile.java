package com.parsdeveloper.shopping.model.entity.cor;

import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;
import com.parsdeveloper.shopping.model.entity.security.User;

import javax.persistence.*;

@Entity
@Table(name = "USER_PROFILE", schema = ApplicationSchema.APPLICATION_SCHEMA)
public class UserProfile extends EffectiveModel<Long> {

    private User user;
    private String mobileNumber;
    private byte[] profileImage;
    private Boolean twoStepEnabled ;

    @Id
    @Column(unique = true, nullable = false, precision = 19)
    @Override
    public Long getId() {
        return super.getId();
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "MOBILE_NUMBER", length = 20)
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Column(name = "TWO_STEP_ENABLED")
    public Boolean getTwoStepEnabled() {
        return twoStepEnabled;
    }

    public void setTwoStepEnabled(Boolean twoStepEnabled) {
        this.twoStepEnabled = twoStepEnabled;
    }

    @Lob
    @Column(name = "PROFILE_IMAGE")
    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }
}