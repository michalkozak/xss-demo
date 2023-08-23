package mk.backbase.xss.api.model;

import java.time.LocalDateTime;

public record UploadRequest(String filename, LocalDateTime fileLastModifiedOn, long size, String mimetype,
                            String hash) {

}
