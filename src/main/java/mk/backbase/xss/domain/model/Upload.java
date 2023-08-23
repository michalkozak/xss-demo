package mk.backbase.xss.domain.model;

import java.time.LocalDateTime;

public record Upload(String filename, LocalDateTime fileLastModifiedOn, long size, String mimetype, String hash) {

}
