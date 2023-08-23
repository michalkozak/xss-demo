package mk.backbase.xss.api;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.backbase.xss.api.mapper.DemoMapper;
import mk.backbase.xss.api.model.DemoRequest;
import mk.backbase.xss.domain.model.Demo;
import mk.backbase.xss.domain.service.DemoService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

@Controller
@RequiredArgsConstructor
@Slf4j
public class JspDemoController {

    private static final String PAGE_SIZE = "50";

    private final DemoMapper demoMapper;
    private final DemoService demoService;

    @GetMapping(path = "/", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public String homepage() {
        return "forward:/demos";
    }

    @GetMapping(path = "/demos", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public String showDemoObjects(ModelMap model,
        @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
        @RequestParam(value = "size", required = false, defaultValue = PAGE_SIZE) Integer size) {

        Page<Demo> demosPage = demoService.getDemos(page, size);
        model.put("demoObjects", demoMapper.mapToDemosResponse(demosPage));
        return "demo-objects";
    }

    @GetMapping(path = "/demos/{id}", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public String showDemoObject(ModelMap model, @PathVariable long id) {
        Demo demo = demoService.getDemo(id);
        model.put("demoObject", demoMapper.mapToDemoResponse(demo));
        return "demo-object";
    }

    @GetMapping(path = "/demos/new", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public String showDemoObjectNewForm() {
        return "demo-object-new";
    }

    @PostMapping(path = "/demos/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.TEXT_HTML_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public String createDemoObject(ModelMap model,
        @RequestPart(name = "name", required = true) String name,
        @RequestPart(name = "description", required = false) String description) {

        DemoRequest demoRequest = new DemoRequest(name, description);
        demoService.createDemo(demoMapper.mapToDemo(demoRequest));
        return showDemoObjects(model);
    }

    @PostMapping(path = "/demos/new", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.TEXT_HTML_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public String createDemoObject(ModelMap model, @RequestBody MultiValueMap<String,String> multiValueMap) {
        DemoRequest demoRequest = new DemoRequest(multiValueMap.getFirst("name"),
            multiValueMap.getFirst("description"));
        demoService.createDemo(demoMapper.mapToDemo(demoRequest));
        return showDemoObjects(model);
    }

    @GetMapping(path = "/demos/upload", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public String showDemoObjectUploadForm() {
        return "demo-object-upload";
    }

    @PostMapping(path = "/demos/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.TEXT_HTML_VALUE)
    @ResponseStatus(code = HttpStatus.OK)
    public String createDemoObject(ModelMap model, @RequestPart(name = "file", required = true) MultipartFile file) {
        String filename = Objects.requireNonNull(file.getOriginalFilename());
        log.info(" filename obtained form-data Content-Disposition: " + filename);
        String decodedFilename = UriUtils.decode(filename, StandardCharsets.UTF_8);
        log.info(" after url-decoding: " + decodedFilename);
        DemoRequest demoRequest = new DemoRequest("file", decodedFilename);
        demoService.createDemo(demoMapper.mapToDemo(demoRequest));
        return showDemoObjects(model);
    }

    private String showDemoObjects(ModelMap model) {
        Page<Demo> demosPage = demoService.getDemos(0, Integer.parseInt(PAGE_SIZE));
        model.put("demoObjects", demoMapper.mapToDemosResponse(demosPage));
        return "demo-objects";
    }

}
