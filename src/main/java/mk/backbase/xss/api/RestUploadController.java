package mk.backbase.xss.api;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import mk.backbase.xss.api.mapper.UploadMapper;
import mk.backbase.xss.api.model.UploadRequest;
import mk.backbase.xss.api.model.UploadResponse;
import mk.backbase.xss.domain.service.UploadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/uploads")
@RequiredArgsConstructor
public class RestUploadController {

    private final UploadMapper uploadMapper;
    private final UploadService uploadService;

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<UploadResponse> initUpload(@RequestBody UploadRequest uploadRequest) {
        String uploadId = uploadService.storeUpload(uploadMapper.mapToUpload(uploadRequest));
        UploadResponse uploadResponse = new UploadResponse(uploadId);
        return ResponseEntity.created(getResourceUri(uploadId)).body(uploadResponse);
    }

    private URI getResourceUri(String id) {
        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentContextPath();
        builder.pathSegment("api/uploads/" + id);
        return builder.build().toUri();
    }

}
