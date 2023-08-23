package mk.backbase.xss.domain.service;

import com.backbase.buildingblocks.presentation.errors.BadRequestException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import mk.backbase.xss.domain.model.Upload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UploadService {

    // cache for demo purpose
    private final Map<String, Upload> cache = new HashMap<>();

    public String storeUpload(Upload upload) {
        String id = UUID.randomUUID().toString();
        cache.put(id, upload);
        return id;
    }

    public Upload getUpload(String id) {
        return Optional.ofNullable(cache.get(id)).orElseThrow(BadRequestException::new);
    }

}
