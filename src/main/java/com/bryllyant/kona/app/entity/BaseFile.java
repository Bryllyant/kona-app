package com.bryllyant.kona.app.entity;

import com.bryllyant.kona.data.entity.KBaseEntity;
import com.bryllyant.kona.http.KMimeTypes;

import java.io.Serializable;

public class BaseFile extends KBaseEntity implements Serializable {
    public enum Type {
        FOLDER,
        THUMBNAIL,
        IMAGE,
        AUDIO,
        VIDEO,
        DOCUMENT,
        ARCHIVE,
        EXECUTABLE,
        OTHER
    }

    public enum Access {
        SYSTEM,
        OWNER,
        FRIEND,
        APP,
        PUBLIC,
        NONE
    }

    public static File.Type getTypeByContentType(String contentType) {
        if (contentType == null) return null;

        for (String type : KMimeTypes.IMAGE_MIME_TYPES) {
            if (contentType.equalsIgnoreCase(type)) {
                return File.Type.IMAGE;
            }
        }

        for (String type : KMimeTypes.AUDIO_MIME_TYPES) {
            if (contentType.equalsIgnoreCase(type)) {
                return File.Type.AUDIO;
            }
        }

        for (String type : KMimeTypes.VIDEO_MIME_TYPES) {
            if (contentType.equalsIgnoreCase(type)) {
                return File.Type.VIDEO;
            }
        }

        for (String type : KMimeTypes.DOCUMENT_MIME_TYPES) {
            if (contentType.equalsIgnoreCase(type)) {
                return File.Type.DOCUMENT;
            }
        }

        for (String type : KMimeTypes.ARCHIVE_MIME_TYPES) {
            if (contentType.equalsIgnoreCase(type)) {
                return File.Type.ARCHIVE;
            }
        }

        if (contentType.equalsIgnoreCase("application/octet-stream")) {
            return File.Type.OTHER;
        }

        return null;
    }

    private byte[] data;

    public byte[] getData() {
        return (data);
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}