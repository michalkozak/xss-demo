package mk.backbase.xss.api.mapper;

import mk.backbase.xss.api.model.UploadRequest;
import mk.backbase.xss.domain.model.Upload;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UploadMapper {

    Upload mapToUpload(UploadRequest uploadRequest);

}
