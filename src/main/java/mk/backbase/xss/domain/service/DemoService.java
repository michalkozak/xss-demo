package mk.backbase.xss.domain.service;

import com.backbase.buildingblocks.presentation.errors.BadRequestException;
import com.backbase.buildingblocks.presentation.errors.InternalServerErrorException;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import mk.backbase.xss.domain.model.Demo;
import mk.backbase.xss.domain.model.Upload;
import mk.backbase.xss.domain.repository.DemoRepository;
import org.apache.commons.codec.binary.Hex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class DemoService {

    private final UploadService uploadService;
    private final DemoRepository demoRepository;

    public Demo createDemo(Demo demo) {
        return demoRepository.save(demo);
    }

    public Demo createDemo(String uploadId, MultipartFile file) {
        Upload upload = uploadService.getUpload(uploadId);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            parse(messageDigest, file.getInputStream());
            String hash = Hex.encodeHexString(messageDigest.digest());
            if (!hash.equals(upload.hash())) {
                throw new BadRequestException("Different file uploaded than initiated!");
            }
        } catch (NoSuchAlgorithmException | IOException e) {
            throw new InternalServerErrorException();
        }
        // for demo purpose
        Demo demo = new Demo();
        demo.setName(upload.hash() + " " + upload.fileLastModifiedOn());
        demo.setDescription(upload.filename());
        return demoRepository.save(demo);
    }

    private void parse(MessageDigest messageDigest, InputStream inputStream) {
        byte[] buffer = new byte[1024];
        try (DigestInputStream digestInputStream = new DigestInputStream(inputStream, messageDigest)) {
            while (digestInputStream.read(buffer, 0, 1024) != -1) { /* do some logic */ }
        } catch (IOException e) {
            throw new InternalServerErrorException();
        }
    }

    public Page<Demo> getDemos(int page, int size) {
        return demoRepository.findAll(PageRequest.of(page, size, Direction.ASC, "id"));
    }

    public Demo getDemo(long id) {
        return demoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
