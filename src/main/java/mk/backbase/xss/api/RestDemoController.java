package mk.backbase.xss.api;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.backbase.xss.api.mapper.DemoMapper;
import mk.backbase.xss.api.model.DemoRequest;
import mk.backbase.xss.api.model.DemoResponse;
import mk.backbase.xss.api.model.DemosResponse;
import mk.backbase.xss.domain.model.Demo;
import mk.backbase.xss.domain.service.DemoService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/demos")
@RequiredArgsConstructor
@Slf4j
public class RestDemoController {

    private final DemoMapper demoMapper;
    private final DemoService demoService;

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<DemoResponse> createDemo(@RequestBody DemoRequest demoRequest) {
        Demo demo = demoService.createDemo(demoMapper.mapToDemo(demoRequest));
        return ResponseEntity.created(getResourceUri(demo.getId())).body(demoMapper.mapToDemoResponse(demo));
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<DemosResponse> getDemos(
        @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
        @RequestParam(value = "size", required = false, defaultValue = "50") Integer size) {
        Page<Demo> demosPage = demoService.getDemos(page, size);
        return ResponseEntity.ok().body(demoMapper.mapToDemosResponse(demosPage));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<DemoResponse> getDemo(@PathVariable long id) {
        Demo demo = demoService.getDemo(id);
        return ResponseEntity.ok().body(demoMapper.mapToDemoResponse(demo));
    }

    @PostMapping(path = "/{uploadId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<DemoResponse> upload(@PathVariable String uploadId,
        @RequestPart(name = "file") MultipartFile file) {
        Demo demo = demoService.createDemo(uploadId, file);
        return ResponseEntity.created(getResourceUri(demo.getId())).body(demoMapper.mapToDemoResponse(demo));
    }

    private URI getResourceUri(long id) {
        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentContextPath();
        builder.pathSegment("api/demos/" + id);
        return builder.build().toUri();
    }

}
