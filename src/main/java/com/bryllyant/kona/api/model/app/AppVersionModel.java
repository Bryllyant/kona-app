package com.bryllyant.kona.api.model.app;

import com.bryllyant.kona.data.model.KJsonModel;

public class AppVersionModel extends KJsonModel {
    private static final long serialVersionUID = 1L;

    private String version;
    private String build;

    public static AppVersionModel create(String version, String build) {
        AppVersionModel model = new AppVersionModel();
        model.setVersion(version);
        model.setBuild(build);
        return model;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.set("version", version);
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.set("build", build);
    }
}

