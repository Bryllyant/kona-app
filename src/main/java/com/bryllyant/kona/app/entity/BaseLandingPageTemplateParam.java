package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;

public class BaseLandingPageTemplateParam extends KBaseEntity {
    public enum Type {
        HTML,
        TEXT,
        URL,
        Media
    }

}