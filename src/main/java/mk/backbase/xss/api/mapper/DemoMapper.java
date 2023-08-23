package mk.backbase.xss.api.mapper;

import java.util.List;
import mk.backbase.xss.api.model.DemoRequest;
import mk.backbase.xss.api.model.DemoResponse;
import mk.backbase.xss.api.model.DemosResponse;
import mk.backbase.xss.domain.model.Demo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DemoMapper {

    Demo mapToDemo(DemoRequest demoRequest);

    DemoResponse mapToDemoResponse(Demo demo);

    List<DemoResponse> mapToDemoResponses(List<Demo> demos);

    @Mapping(target = "hits", source = "totalElements")
    @Mapping(target = "demos", source = "content")
    DemosResponse mapToDemosResponse(Page<Demo> demosPage);

}
